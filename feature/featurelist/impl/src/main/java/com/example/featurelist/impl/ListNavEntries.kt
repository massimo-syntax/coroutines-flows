package com.example.featurelist.impl

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.featurelist.ListRoute
import com.example.presentation.featureslist.FeatureListScreen


@Composable
fun EntryProviderScope<NavKey>.ListRoutes(backStack: NavBackStack<NavKey>) {
    entry<ListRoute>{
        FeatureListScreen{ navKey -> backStack.add(navKey) }
    }
}