package com.example.presentation.coroutines.screen.sequentialCoroutines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AndroidFeature
import com.example.domain.repository.CoroutinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed interface FeaturesUiState {
    data object Initial : FeaturesUiState
    data object Loading : FeaturesUiState
    data class Success(
        val allFeatures: List<AndroidFeature>,
        val filteredFeatures: List<AndroidFeature>,
        val searchQuery: String = ""
    ) : FeaturesUiState
}

@HiltViewModel
class FeaturesViewModel @Inject constructor(
    private val repository: CoroutinesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<FeaturesUiState>(FeaturesUiState.Initial)
    val uiState: StateFlow<FeaturesUiState> = _uiState.asStateFlow()

    /**
     * Triggered by the "Load Latest Features" button on the initial screen.
     */
    fun loadFeatures() {
        viewModelScope.launch {
            _uiState.value = FeaturesUiState.Loading
            try {
                val data = repository.getSequentialFeaturesNewestAndroidVersions()
                _uiState.value = FeaturesUiState.Success(
                    allFeatures = data,
                    filteredFeatures = data
                )
            } catch (e: Exception) {
                // Ignore or handle
            }
        }
    }

    fun reload() {
        loadFeatures()
    }

    /**
     * Filters list based on search text input.
     */
    fun onSearchQueryChanged(query: String) {
        val currentState = _uiState.value as? FeaturesUiState.Success ?: return
        val filtered = if (query.isBlank()) {
            currentState.allFeatures
        } else {
            currentState.allFeatures.filter { feature ->
                feature.title.contains(query, ignoreCase = true) ||
                feature.description.contains(query, ignoreCase = true) ||
                feature.tag.contains(query, ignoreCase = true)
            }
        }

        _uiState.update {
            currentState.copy(
                searchQuery = query,
                filteredFeatures = filtered
            )
        }
    }
}
