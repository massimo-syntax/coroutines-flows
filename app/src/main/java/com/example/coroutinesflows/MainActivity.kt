package com.example.coroutinesflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import com.example.core.preferences.datasourcedatastore.PreferencesDataSource
import com.example.coroutinesflows.designsystem.theme.CoroutinesFlowsTheme
import com.example.navigation.Nav
import dagger.hilt.android.AndroidEntryPoint
import jakarta.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var themeDataSource: PreferencesDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val theme = themeDataSource.getThemeBlocking()


        enableEdgeToEdge()
        setContent {
            val appState = rememberAppState(
                initialTheme = theme,
                themeDataSource = themeDataSource
            )

            CoroutinesFlowsTheme(appState.currentTheme) {
                // access to the app state directly from the composable
                CompositionLocalProvider(LocalAppState provides appState) {
                    Nav()
                }
            }

        }
    }
}


