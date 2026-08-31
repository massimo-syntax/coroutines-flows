package com.example.core.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.core.preferences.model.AppTheme

/**
 * Fast in-memory cache for theme/corner radius.
 * Synced from SharedPreferences on first access (~1ms read).
 * DataStore updates this in background without blocking UI.
 */
@Suppress("MemberVisibilityCanBePrivate")
class PreferencesCache(context: Context) {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences("app_prefs_cache", Context.MODE_PRIVATE)

    // Cached values (loaded once from SharedPrefs on first access)
    var theme: AppTheme = loadTheme()
        private set

    var cornerRadius: Int = loadCornerRadius()
        private set

    // Fast blocking reads (from memory after first load)
    private fun loadTheme(): AppTheme {
        val themeName = sharedPrefs.getString("theme", "SYSTEM") ?: "SYSTEM"
        return try {
            AppTheme.valueOf(themeName)
        } catch (e: Exception) {
            AppTheme.SYSTEM
        }
    }

    private fun loadCornerRadius(): Int {
        return sharedPrefs.getInt("corner_radius", 8)
    }

    // Called by DataSource when user changes theme (background thread safe)
    fun updateTheme(newTheme: AppTheme) {
        theme = newTheme
        sharedPrefs.edit().putString("theme", newTheme.name).apply()
    }

    fun updateCornerRadius(newRadius: Int) {
        cornerRadius = newRadius
        sharedPrefs.edit().putInt("corner_radius", newRadius).apply()
    }
}
