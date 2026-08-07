package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.example.feature.coroutines.impl.CoroutinesRoutes
import com.example.feature.flows.impl.FlowsRoutes
import com.example.featurelist.ListRoute
import com.example.featurelist.impl.ListRoutes
import com.example.myfeature.impl.MyFeatureRoutes


@Composable
fun Nav(){

    val backStack = rememberNavBackStack(ListRoute)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            ListRoutes(backStack)
            MyFeatureRoutes(backStack)
            CoroutinesRoutes(backStack)
            FlowsRoutes(backStack)
        }
    )

}