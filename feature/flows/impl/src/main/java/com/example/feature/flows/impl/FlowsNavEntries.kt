package com.example.feature.flows.impl

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.flows.api.VeryFastFlow
import com.example.flows.api.VerySimpleFLow
import com.example.presentation.flows.screen.VeryFastFlow
import com.example.presentation.flows.screen.VerySimpleFlow

@Composable
fun EntryProviderScope<NavKey>.FlowsRoutes(backStack: NavBackStack<NavKey>) {
    entry<VerySimpleFLow>{
        VerySimpleFlow()
    }
    entry<VeryFastFlow>{
        VeryFastFlow()
    }
}