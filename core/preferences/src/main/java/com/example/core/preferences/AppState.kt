package com.example.coroutinesflows

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.core.preferences.datasourcedatastore.PreferencesDataSource
import com.example.designsystem.theme.thememodel.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Stable
class AppState(
    initialTheme: AppTheme,
    private val themeDataSource: PreferencesDataSource,
    private val coroutineScope: CoroutineScope
) {
    var currentTheme by mutableStateOf(initialTheme)
        private set

    init {
        // keep state in sync with DataStore going forward
        coroutineScope.launch {
            themeDataSource.themeFlow.collect { theme ->
                currentTheme = theme
            }
        }
    }

    fun setTheme(theme: AppTheme) {
        coroutineScope.launch {
            themeDataSource.setTheme(theme)
            // no need to manually set currentTheme — the collect above will update it
        }
    }
}

@Composable
fun rememberAppState(
    initialTheme: AppTheme,
    themeDataSource: PreferencesDataSource,
    coroutineScope: CoroutineScope = rememberCoroutineScope()
): AppState {
    return remember {
        AppState(
            initialTheme = initialTheme,
            themeDataSource = themeDataSource,
            coroutineScope = coroutineScope
        )
    }
}

val LocalAppState = staticCompositionLocalOf<AppState> {
    error("AppState not provided")
}
