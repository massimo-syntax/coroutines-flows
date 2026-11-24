package com.example.featurelist.api

import androidx.navigation3.runtime.NavKey
import com.example.feature.coroutines.api.AddAgentSkills
import com.example.feature.coroutines.api.RetryExponentialCoroutines
import com.example.feature.coroutines.api.SequentialCoroutines
import com.example.feature.coroutines.api.TimeoutCoroutine
import com.example.feature.coroutines.api.VeryComplicatedCoroutine
import com.example.feature.coroutines.api.VerySimpleCoroutine
import com.example.flows.api.VeryFastFlow
import com.example.flows.api.VerySimpleFLow

enum class SelectedCategory {
    Coroutines,
    Flows,
    All
}

data class FeatureItem(
    val title: String,
    val subtitle: String = "",
    val description: String = "",
    val imageUrl: String = "",
    val tags: List<String> = emptyList(),
    val type: SelectedCategory,
    val navKey: NavKey
)

val navigableItems = listOf(
    // coroutines
    FeatureItem(
        type = SelectedCategory.Coroutines,
        title = "Simple coroutine",
        navKey = VerySimpleCoroutine
    ),
    FeatureItem(
        type = SelectedCategory.Coroutines,
        title = "Complicated Coroutine",
        navKey = VeryComplicatedCoroutine
    ),
    FeatureItem(
        type = SelectedCategory.Coroutines,
        title = "Sequential Coroutines",
        navKey = SequentialCoroutines
    ),
    FeatureItem(
        type = SelectedCategory.Coroutines,
        title = "Timeout Showcase",
        subtitle = "Coroutines Timeout",
        description = "Demonstration of withTimeout, withTimeoutOrNull, and select onTimeout",
        navKey = TimeoutCoroutine
    ),
    FeatureItem(
        type = SelectedCategory.Coroutines,
        title = "Add Agent Skills",
        subtitle = "Coroutines Flow",
        description = "Sample data flow created using new-data-flow skill",
        navKey = AddAgentSkills
    ),
    FeatureItem(
        type = SelectedCategory.Coroutines,
        title = "Retry network request",
        subtitle = "Retry with exponential backoff",
        description = "Delegate the network call function to a retry function, that retries the request up to 3 time",
        navKey = RetryExponentialCoroutines
    ),

    // flows
    FeatureItem(
        type = SelectedCategory.Flows,
        title = "Simple Flow",
        navKey = VerySimpleFLow
    ),
    FeatureItem(
        type = SelectedCategory.Flows,
        title = "Simple Flow",
        navKey = VeryFastFlow
    )
)
