package com.example.core.preferences.datasourcedatastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.designsystem.theme.thememodel.AppTheme
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking


@Singleton
class PreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    private object Keys {
        val APP_THEME = stringPreferencesKey("app_theme")
    }

    val themeFlow: Flow<AppTheme> = dataStore.data
        .catch { e ->
            if (e is IOException) emit(emptyPreferences()) else throw e
        }
        .map { prefs ->
            prefs[Keys.APP_THEME]
                ?.let { runCatching { AppTheme.valueOf(it) }.getOrNull() }
                ?: AppTheme.SYSTEM
        }

    suspend fun setTheme(theme: AppTheme) {
        dataStore.edit { it[Keys.APP_THEME] = theme.name }
    }

    /** Blocking read — use only once, for the very first value at process start. */
    fun getThemeBlocking(): AppTheme = runBlocking { themeFlow.first() }
}