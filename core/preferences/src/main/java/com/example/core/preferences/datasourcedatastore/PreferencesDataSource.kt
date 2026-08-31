package com.example.core.preferences.datasourcedatastore

import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.preferences.PreferencesCache
import com.example.core.preferences.model.AppTheme
import jakarta.inject.Inject
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


@Singleton
class PreferencesDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
    private val preferencesCache: PreferencesCache
) {
    private object Keys {
        val APP_THEME = stringPreferencesKey("app_theme")
        val CORNER_RADIUS = intPreferencesKey("corner_radius")
        val NOTIFICATIONS = booleanPreferencesKey("notifications")
        val HAPTICS = booleanPreferencesKey("haptics")
    }

    // theme
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
        preferencesCache.updateTheme(theme) // Sync cache
    }

    // corner radius
    val cornerRadiusFlow: Flow<Int> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { it[Keys.CORNER_RADIUS] ?: 8 }

    suspend fun setCornerRadius(radius: Int) {
        dataStore.edit { it[Keys.CORNER_RADIUS] = radius }
        preferencesCache.updateCornerRadius(radius) // Sync cache
    }

    suspend fun getCornerRadius(): Int = cornerRadiusFlow.first()

    // notifications
    val notificationsFlow: Flow<Boolean> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { it[Keys.NOTIFICATIONS] ?: true }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        dataStore.edit { it[Keys.NOTIFICATIONS] = enabled }
    }

    suspend fun isNotificationsEnabled(): Boolean = notificationsFlow.first()

    // haptics
    val hapticsFlow: Flow<Boolean> = dataStore.data
        .catch { e -> if (e is IOException) emit(emptyPreferences()) else throw e }
        .map { it[Keys.HAPTICS] ?: true }

    suspend fun setHapticsEnabled(enabled: Boolean) {
        dataStore.edit { it[Keys.HAPTICS] = enabled }
    }

    suspend fun isHapticsEnabled(): Boolean = hapticsFlow.first()
}
