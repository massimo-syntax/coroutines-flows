package com.example.featurelist.ui

import androidx.navigation3.runtime.NavKey
import com.example.feature.coroutines.api.VeryComplicatedCoroutine
import com.example.feature.coroutines.api.VerySimpleCoroutine
import com.example.flows.api.VeryFastFlow
import com.example.flows.api.VerySimpleFLow


sealed interface FeatureItem

enum class SelectedCategory{
    Coroutines,
    Flows,
    All
}

data class ItemCoroutine(
    val title: String,
    val subtitle: String= "",
    val description: String = "",
    val imageUrl: String = "",
    val type: SelectedCategory = SelectedCategory.Coroutines,
    val navKey: NavKey
) : FeatureItem

data class ItemFlow(
    val title: String,
    val subtitle: String= "",
    val description: String = "",
    val imageUrl: String = "",
    val type: SelectedCategory = SelectedCategory.Flows,
    val navKey: NavKey
) : FeatureItem

val navigateableCoroutineItems = listOf<ItemCoroutine>(
    // coroutines
    ItemCoroutine(
        title = "Simple coroutine",
        navKey = VerySimpleCoroutine
    ),
    ItemCoroutine(
        title = "Complicated Coroutine",
        navKey = VeryComplicatedCoroutine
    ),
)

val navigateableFlowItems = listOf<ItemFlow>(
    // flows
    ItemFlow(
        title = "Simple Flow",
        navKey = VerySimpleFLow
    ),
    ItemFlow(
        title = "Simple Flow",
        navKey = VeryFastFlow
    )
)
