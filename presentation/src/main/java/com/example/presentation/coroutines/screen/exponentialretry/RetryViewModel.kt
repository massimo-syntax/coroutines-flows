package com.example.presentation.coroutines.screen.exponentialretry


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NetworkUiState(
    val isRunning: Boolean = false,
    val elapsedMillis: Long = 0L,
    val attempts: Int = 0,
    val isSuccess: Boolean? = null,
    val statusMessage: String = "Ready to send request",
    val responsePayload: String? = null
)

@HiltViewModel
class NetworkViewModel @Inject constructor(): ViewModel() {

    // Backing StateFlow observed by Compose via collectAsState() to re-render the UI
    private val _uiState = MutableStateFlow(NetworkUiState())
    val uiState: StateFlow<NetworkUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    /**
     * Triggered when the user taps the button:
     * 1. Resets state and starts the elapsed timer.
     * 2. Calls retryWithExponentialBackoff() with ::updateRetryState as the UI callback.
     * 3. Stops the timer and publishes the final result when the 3rd attempt succeeds.
     */
    fun performNetworkRequest() {
        if (_uiState.value.isRunning) return

        _uiState.value = NetworkUiState(
            isRunning = true,
            statusMessage = "Sending request (attempt 1)..."
        )
        startTimer()

        viewModelScope.launch {
            var simulatedAttempt = 0
            try {
                // Separate retry helper tries 3 times; fails first 2, succeeds on 3rd
                val result = retryWithExponentialBackoff(
                    times = 3,
                    initialDelayMillis = 1000L,
                    factor = 2.0,
                    onRetryUpdate = ::updateRetryState
                ) {
                    simulatedAttempt++
                    delay(220L)
                    if (simulatedAttempt <= 2) {
                        throw IllegalStateException("Failure $simulatedAttempt")
                    }
                    "200 OK · Payload synchronized"
                }

                // Stop timer as soon as retryWithExponentialBackoff returns success
                stopTimer()
                _uiState.update {
                    it.copy(
                        isRunning = false,
                        isSuccess = true,
                        responsePayload = result,
                        statusMessage = "Succeeded on attempt ${it.attempts}"
                    )
                }
            } catch (e: Exception) {
                stopTimer()
                _uiState.update {
                    it.copy(
                        isRunning = false,
                        isSuccess = false,
                        statusMessage = e.message ?: "Request failed"
                    )
                }
            }
        }
    }

    // Callback passed to retryWithExponentialBackoff; updates StateFlow so the UI recomposes
    private fun updateRetryState(attempt: Int, isSuccess: Boolean) {
        _uiState.update { state ->
            state.copy(
                attempts = attempt,
                isSuccess = isSuccess,
                statusMessage = if (isSuccess) {
                    "Attempt $attempt: Success"
                } else {
                    "Attempt $attempt failed — backing off..."
                }
            )
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        val startTime = System.currentTimeMillis()
        timerJob = viewModelScope.launch {
            while (true) {
                // Emits new elapsedMillis every 50ms to update the timer text in the UI
                _uiState.update { it.copy(elapsedMillis = System.currentTimeMillis() - startTime) }
                delay(50L)
            }
        }
    }

    private fun stopTimer() {
        timerJob?.cancel()
        timerJob = null
    }
}