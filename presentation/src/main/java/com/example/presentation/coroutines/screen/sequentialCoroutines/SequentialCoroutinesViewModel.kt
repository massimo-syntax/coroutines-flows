package com.example.presentation.coroutines.screen.sequentialCoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.repository.CoroutinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class SequentialCoroutinesViewModel @Inject constructor(
    private val repository: CoroutinesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<SequentialCoroutinesUiState>(SequentialCoroutinesUiState.Initial)
    val uiState: StateFlow<SequentialCoroutinesUiState> = _uiState.asStateFlow()


    fun mountListAndLoad() {
        viewModelScope.launch {
            // Mount the list screen with both loaders active
            _uiState.value = SequentialCoroutinesUiState.ListScreen(
                isVersionLoading = true,
                lastAndroidVersion = null,
                isFeaturesLoading = true,
                features = emptyList()
            )

            try {
                // Step 1: Sequential call to fetch newest versions & take last
                val versions = repository.getSequentialNewestAndroidVersions()
                delay(0.5.seconds)
                val latestVersion = versions.last().title // "36"

                // State update 1: Version loaded (Version loader clears -> Version banner displays)
                val currentScreen = _uiState.value as? SequentialCoroutinesUiState.ListScreen
                if (currentScreen != null) {
                    _uiState.value = currentScreen.copy(
                        isVersionLoading = false,
                        lastAndroidVersion = latestVersion
                    )
                }

                // Step 2: Sequential call to fetch features (runs with version already visible)
                val data = repository.getSequentialFeaturesNewestAndroidVersions()
                delay(0.5.seconds)
                // State update 2: Features loaded (List loader clears -> cards display)
                val updatedScreen = _uiState.value as? SequentialCoroutinesUiState.ListScreen
                if (updatedScreen != null) {
                    _uiState.value = updatedScreen.copy(
                        isFeaturesLoading = false,
                        features = data
                    )
                }
            } catch (e: Exception) {
                val current = _uiState.value as? SequentialCoroutinesUiState.ListScreen
                if (current != null) {
                    _uiState.value = current.copy(
                        isVersionLoading = false,
                        isFeaturesLoading = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }

    /**
     * Reload action on the mounted list screen
     */
    fun reload() {
        mountListAndLoad()
    }

    /**
     * Reset back to initial screen with code block
     */
    fun resetToInitial() {
        _uiState.value = SequentialCoroutinesUiState.Initial
    }
}
