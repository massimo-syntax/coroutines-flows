package com.example.feature.usersettings.impl

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.example.feature.usersettings.api.ProfileKey
import com.example.feature.usersettings.api.SettingsKey
import com.example.feature.usersettings.ui.screen.ProfileScreen
import com.example.feature.usersettings.ui.screen.SettingsScreen

@Composable
fun EntryProviderScope<NavKey>.UserSettingsRoutes(backStack: NavBackStack<NavKey>) {
    entry<SettingsKey>{
        SettingsScreen()
    }
    entry<ProfileKey>{
        ProfileScreen()
    }
}