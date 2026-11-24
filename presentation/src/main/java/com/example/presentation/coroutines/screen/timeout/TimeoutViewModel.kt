package com.example.presentation.coroutines.screen.timeout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.onTimeout
import kotlinx.coroutines.selects.select
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

@HiltViewModel
class TimeoutViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(TimeoutShowcaseState())
    val uiState: StateFlow<TimeoutShowcaseState> = _uiState.asStateFlow()

    private var activeJob: Job? = null

    // Network delay slider update (fixed 4s max)
    fun updateNetworkDelay(delayMs: Long) {
        _uiState.update { it.copy(networkDelayMs = delayMs.coerceIn(100L, 4_000L)) }
    }

    // Custom timeout slider update (fixed 4s max)
    fun updateTimeoutBudget(timeoutMs: Long) {
        _uiState.update { it.copy(timeoutMs = timeoutMs.coerceIn(100L, 4_000L)) }
    }

    // Button 1: Throws TimeoutCancellationException on expiry
    fun runWithTimeout() {
        activeJob?.cancel() // Cancel any previous run
        val snapshot = _uiState.value
        val budgetMs = snapshot.timeoutMs
        val startMs = System.currentTimeMillis()

        activeJob = viewModelScope.launch {
            _uiState.update { // Show loader and reset stopwatch
                it.copy(
                    activeMode = TimeoutMode.WITH_TIMEOUT_MS,
                    result = TimeoutResult.Loading,
                    elapsedMs = 0L
                )
            }

            val tickerJob = launch { // Increase live timer & race bar
                while (true) {
                    _uiState.update { it.copy(elapsedMs = System.currentTimeMillis() - startMs) }
                    delay(16L)
                }
            }

            try {
                val payload = withTimeout(budgetMs) { // Abort with exception if delay > budgetMs
                    simulatedNetworkCall(snapshot.networkDelayMs)
                }
                val finalMs = System.currentTimeMillis() - startMs
                _uiState.update { // Write completed time into Compose card
                    it.copy(
                        elapsedMs = finalMs,
                        result = TimeoutResult.Success(
                            payload = payload,
                            elapsedMs = finalMs,
                            mode = TimeoutMode.WITH_TIMEOUT_MS
                        )
                    )
                }
            } catch (e: TimeoutCancellationException) {
                val finalMs = System.currentTimeMillis() - startMs
                _uiState.update { // Trigger animated error card in Compose
                    it.copy(
                        elapsedMs = finalMs,
                        result = TimeoutResult.Error(
                            reason = e.message ?: "Timed out after ${budgetMs} ms",
                            elapsedMs = finalMs,
                            mode = TimeoutMode.WITH_TIMEOUT_MS
                        )
                    )
                }
            } finally {
                tickerJob.cancel() // Stop live timer
            }
        }
    }

    // Button 2: Returns null cleanly instead of throwing
    fun runWithTimeoutOrNull() {
        activeJob?.cancel() // Cancel any previous run
        val snapshot = _uiState.value
        val budgetMs = snapshot.timeoutMs
        val startMs = System.currentTimeMillis()

        activeJob = viewModelScope.launch {
            _uiState.update { // Show loader and reset stopwatch
                it.copy(
                    activeMode = TimeoutMode.WITH_TIMEOUT_OR_NULL_MS,
                    result = TimeoutResult.Loading,
                    elapsedMs = 0L
                )
            }

            val tickerJob = launch { // Increase live timer & race bar
                while (true) {
                    _uiState.update { it.copy(elapsedMs = System.currentTimeMillis() - startMs) }
                    delay(16L)
                }
            }

            try {
                val payload = withTimeoutOrNull(budgetMs) { // Emit null if delay > budgetMs
                    simulatedNetworkCall(snapshot.networkDelayMs)
                }
                val finalMs = System.currentTimeMillis() - startMs
                _uiState.update { // Write completed time or null fallback to Compose
                    it.copy(
                        elapsedMs = finalMs,
                        result = if (payload != null) {
                            TimeoutResult.Success(
                                payload = payload,
                                elapsedMs = finalMs,
                                mode = TimeoutMode.WITH_TIMEOUT_OR_NULL_MS
                            )
                        } else {
                            TimeoutResult.TimedOutNull(
                                elapsedMs = finalMs,
                                mode = TimeoutMode.WITH_TIMEOUT_OR_NULL_MS
                            )
                        }
                    )
                }
            } finally {
                tickerJob.cancel() // Stop live timer
            }
        }
    }

    // Button 3: Races async request against an onTimeout branch
    @OptIn(ExperimentalCoroutinesApi::class)
    fun runWithSelectTimeout() {
        activeJob?.cancel() // Cancel any previous run
        val snapshot = _uiState.value
        val budgetMs = snapshot.timeoutMs
        val startMs = System.currentTimeMillis()

        activeJob = viewModelScope.launch {
            _uiState.update { // Show loader and reset stopwatch
                it.copy(
                    activeMode = TimeoutMode.SELECT_ON_TIMEOUT_MS,
                    result = TimeoutResult.Loading,
                    elapsedMs = 0L
                )
            }

            val tickerJob = launch { // Increase live timer & race bar
                while (true) {
                    _uiState.update { it.copy(elapsedMs = System.currentTimeMillis() - startMs) }
                    delay(16L)
                }
            }

            try {
                val request = async { simulatedNetworkCall(snapshot.networkDelayMs) }
                val payload = select { // First branch to finish wins
                    request.onAwait { it }
                    onTimeout(budgetMs) { // Cancel async call and return fallback
                        request.cancel()
                        null
                    }
                }
                val finalMs = System.currentTimeMillis() - startMs
                _uiState.update { // Write completed time or fallback state to Compose
                    it.copy(
                        elapsedMs = finalMs,
                        result = if (payload != null) {
                            TimeoutResult.Success(
                                payload = payload,
                                elapsedMs = finalMs,
                                mode = TimeoutMode.SELECT_ON_TIMEOUT_MS
                            )
                        } else {
                            TimeoutResult.TimedOutNull(
                                elapsedMs = finalMs,
                                mode = TimeoutMode.SELECT_ON_TIMEOUT_MS
                            )
                        }
                    )
                }
            } finally {
                tickerJob.cancel() // Stop live timer
            }
        }
    }

    // Cancel button: stop active coroutine and reset UI
    fun cancelActive() {
        activeJob?.cancel()
        _uiState.update { it.copy(result = TimeoutResult.Idle) }
    }

    // Fake network suspension for the chosen delay
    private suspend fun simulatedNetworkCall(delayMs: Long): String {
        delay(delayMs)
        return "200 OK · Payload synced in ${delayMs} ms"
    }
}