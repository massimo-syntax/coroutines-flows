package com.example.designsystem.theme.thememodel

enum class AppTheme {
    SYSTEM,          // follow system light/dark, default palette
    LIGHT_DEFAULT,
    LIGHT_OCEAN,
    LIGHT_SUNSET,
    DARK_DEFAULT,
    DARK_MIDNIGHT,
    DARK_FOREST;

    val isDark: Boolean
        get() = this in listOf(DARK_DEFAULT, DARK_MIDNIGHT, DARK_FOREST)
}