package com.example.feature.coroutines.impl

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.feature.coroutines.api.VeryComplicatedCoroutine
import com.example.feature.coroutines.api.VerySimpleCoroutine
import com.example.presentation.coroutines.screen.SimpleCoroutine
import com.example.presentation.coroutines.screen.VeryComplicatedCoroutine

@Composable
fun EntryProviderScope<NavKey>.CoroutinesRoutes(backStack: NavBackStack<NavKey>) {
    entry<VerySimpleCoroutine>{
        SimpleCoroutine()
    }
    entry<VeryComplicatedCoroutine>{
        VeryComplicatedCoroutine()
    }
}