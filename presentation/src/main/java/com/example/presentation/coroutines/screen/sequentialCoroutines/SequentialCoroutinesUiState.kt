package com.example.presentation.coroutines.screen.sequentialCoroutines

import com.example.domain.model.AndroidFeature

sealed interface SequentialCoroutinesUiState {
    /**
     * Initial screen with post-style code block and mount button
     */
    data object Initial : SequentialCoroutinesUiState

    /**
     * Mounted screen with the list containing independent version and list loaders
     */
    data class ListScreen(
        val isVersionLoading: Boolean = true,
        val lastAndroidVersion: String? = null,
        val isFeaturesLoading: Boolean = true,
        val features: List<AndroidFeature> = emptyList(),
        val errorMessage: String? = null
    ) : SequentialCoroutinesUiState
}
