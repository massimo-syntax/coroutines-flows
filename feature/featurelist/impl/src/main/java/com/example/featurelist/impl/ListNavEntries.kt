package com.example.featurelist.impl

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.compose.ui.screens.NavigationListScreen
import com.example.feature.usersettings.api.ProfileKey
import com.example.feature.usersettings.api.SettingsKey
import com.example.featurelist.ListRoute


@Composable
fun EntryProviderScope<NavKey>.ListRoutes(backStack: NavBackStack<NavKey>) {
    entry<ListRoute>{
        NavigationListScreen(
            onNavigateToProfile = { backStack.add(ProfileKey) },
            onNavigateToSettings = { backStack.add(SettingsKey) },
            navigateToShowcaseScreen = { navKey -> backStack.add(navKey) }
        )
    }
}