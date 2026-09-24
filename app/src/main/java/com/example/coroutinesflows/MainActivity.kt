package com.example.coroutinesflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.core.preferences.LocalAppState
import com.example.core.preferences.datasourcedatastore.PreferencesDataSource
import com.example.core.preferences.model.AppTheme
import com.example.core.preferences.rememberAppState
import com.example.coroutinesflows.designsystem.theme.CoroutinesFlowsTheme
import com.example.navigation.Nav
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var themeDataSource: PreferencesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                themeDataSource.themeFlow.collect { currentTheme ->
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(0, 0) { currentTheme.isDark },
                        navigationBarStyle = SystemBarStyle.auto(0, 0) { currentTheme.isDark }
                    )
                }
            }
        }

        val theme = runBlocking { themeDataSource.themeFlow.first() }
        val cornerShapeValue = runBlocking { themeDataSource.cornerRadiusFlow.first() }
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(0, 0) { theme.isDark },
            navigationBarStyle = SystemBarStyle.auto(0, 0) { theme.isDark }
        )

        setContent {
            val appState = rememberAppState(
                initialTheme = theme,
                initialCornerRadius = cornerShapeValue,
                preferencesDataSource = themeDataSource
            )

            CoroutinesFlowsTheme(
                appTheme = appState.currentTheme,
                cornerRadius = appState.cornerRadiusValue
            ) {
                // access to the app state directly from the composable
                CompositionLocalProvider(LocalAppState provides appState) {
                    Nav()
                }
            }
        }
    }
}
