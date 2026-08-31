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
import com.example.core.preferences.PreferencesCache
import com.example.core.preferences.datasourcedatastore.PreferencesDataSource
import com.example.coroutinesflows.designsystem.theme.CoroutinesFlowsTheme
import com.example.navigation.Nav
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var themeDataSource: PreferencesDataSource

    @Inject
    lateinit var preferencesCache: PreferencesCache

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FRAME 0: Load from cache instantly (~1ms, no I/O blocking)
        val cachedTheme = preferencesCache.theme
        val cachedRadius = preferencesCache.cornerRadius

        // Apply system bar style with cached theme BEFORE setContent
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(0, 0) { cachedTheme.isDark },
            navigationBarStyle = SystemBarStyle.auto(0, 0) { cachedTheme.isDark }
        )

        // 1. Observe state changes natively inside the lifecycle
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Assuming your theme data source exposes a flow
                themeDataSource.themeFlow.collect { currentTheme ->
                    // 2. Safely call it outside Compose without any side-effect issues
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.auto(0, 0) { currentTheme.isDark },
                        navigationBarStyle = SystemBarStyle.auto(0, 0) { currentTheme.isDark }
                    )
                }
            }
        }

        setContent {
            val appState = rememberAppState(
                initialTheme = cachedTheme,
                initialCornerRadius = cachedRadius,
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
