package com.example.coroutinesflows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.core.preferences.datasourcedatastore.PreferencesDataSource
import com.example.core.preferences.model.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Stable
class AppState(
    initialTheme: AppTheme,
    initialCornerRadius: Int,
    private val preferencesDataSource: PreferencesDataSource,
    private val coroutineScope: CoroutineScope
) {
    var currentTheme by mutableStateOf(initialTheme)
        private set

    var cornerRadiusValue by mutableIntStateOf(initialCornerRadius)
        private set

    var areNotificationsEnabled by mutableStateOf(true) // Initialized with default, updated via flow
        private set

    var areHapticsEnabled by mutableStateOf(true) // Initialized with default, updated via flow
        private set

    init {
        // keep state in sync with DataStore going forward
        coroutineScope.launch {
            preferencesDataSource.themeFlow.collect { theme ->
                currentTheme = theme
            }
        }
        coroutineScope.launch {
            preferencesDataSource.cornerRadiusFlow.collect { radius ->
                cornerRadiusValue = radius
            }
        }
        coroutineScope.launch {
            preferencesDataSource.notificationsFlow.collect { enabled ->
                areNotificationsEnabled = enabled
            }
        }
        coroutineScope.launch {
            preferencesDataSource.hapticsFlow.collect { enabled ->
                areHapticsEnabled = enabled
            }
        }
    }

    fun setTheme(theme: AppTheme) {
        coroutineScope.launch {
            preferencesDataSource.setTheme(theme)
        }
    }

    fun setCornerRadius(radius: Int) {
        coroutineScope.launch {
            preferencesDataSource.setCornerRadius(radius)
        }
    }

    fun setNotificationsEnabled(enabled: Boolean) {
        coroutineScope.launch {
            preferencesDataSource.setNotificationsEnabled(enabled)
        }
    }

    fun setHapticsEnabled(enabled: Boolean) {
        coroutineScope.launch {
            preferencesDataSource.setHapticsEnabled(enabled)
        }
    }
}

@Composable
fun rememberAppState(
    initialTheme: AppTheme,
    initialCornerRadius: Int,
    preferencesDataSource: PreferencesDataSource,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
    return remember {
        AppState(
            initialTheme = initialTheme,
            initialCornerRadius = initialCornerRadius,
            preferencesDataSource = preferencesDataSource,
            coroutineScope = coroutineScope
        )
    }
}

val LocalAppState = staticCompositionLocalOf<AppState> {
    error("AppState not provided")
}
