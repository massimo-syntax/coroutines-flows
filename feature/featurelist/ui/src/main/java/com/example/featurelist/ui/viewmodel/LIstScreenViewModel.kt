package com.example.featurelist.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featurelist.ui.FeatureItem
import com.example.featurelist.ui.SelectedCategory
import com.example.featurelist.ui.navigateableItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

// FeaturesViewModel.kt
class ListScreenViewModel : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    private val _selectedCategory = MutableStateFlow(SelectedCategory.All)
    val selectedCategory: StateFlow<SelectedCategory> = _selectedCategory.asStateFlow()

    val uiState: StateFlow<UiState> =
        combine(_query, _selectedCategory) { query, category ->
            query to category
        }
            .map { (query, category) ->
                val items = navigateableItems.filter { feature ->
                    val matchesCategory =
                        category == SelectedCategory.All || feature.type == category
                    val matchesQuery = query.isBlank() ||
                            feature.title.contains(query, ignoreCase = true) ||
                            feature.subtitle.contains(query, ignoreCase = true) ||
                            feature.description.contains(query, ignoreCase = true)

                    matchesCategory && matchesQuery
                }
                // update uiState with filtered items and last category
                UiState(items, category)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = UiState(navigateableItems, SelectedCategory.All)
            )

    fun onQueryChanged(newQuery: String) {
        _query.value = newQuery
    }

    fun onCategorySelected(category: SelectedCategory) {
        _selectedCategory.value = category
    }
}

data class UiState(
    val itemsList: List<FeatureItem>,
    val selectedCategory: SelectedCategory
)
