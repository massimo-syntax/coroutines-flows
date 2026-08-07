package com.example.myfeature.impl


import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.coroutinesflows.presentation.flows.screen.MyScreen

import com.example.myfetaure.api.MyFeatureRoute


@Composable
fun EntryProviderScope<NavKey>.MyFeatureRoutes(backStack: NavBackStack<NavKey>) {
    entry<MyFeatureRoute>{
        MyScreen()
    }
}