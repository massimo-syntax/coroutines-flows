package com.example.featurelist.ui

import androidx.navigation3.runtime.NavKey
import com.example.feature.coroutines.api.VeryComplicatedCoroutine
import com.example.feature.coroutines.api.VerySimpleCoroutine
import com.example.flows.api.VeryFastFlow
import com.example.flows.api.VerySimpleFLow


// define a list of "Feature Items"
// to display them in the lazy list
// EVERY ITEM NAVIGATES TO A DIFFERENT SCREEN
// so EVERY item has also a unique NavKey
// that is used for navigation
// the NavEntry provider in :featurelist:impl module passes a callback
// for the back stack, being completely different screens
// is needed to give the NavKey as parameter in the onClick of every list item.
// Important:
// ... for every job offer I can differ the architecture of the app as required.
enum class SelectedCategory{
    Coroutines,
    Flows,
    All
}



data class FeatureItem(
     val title: String,
     val subtitle: String= "",
     val description: String = "",
     val imageUrl: String = "",
     val tags: List<String> = emptyList(),
     val type: SelectedCategory,
     val navKey: NavKey
)
val navigateableItems = listOf(

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

