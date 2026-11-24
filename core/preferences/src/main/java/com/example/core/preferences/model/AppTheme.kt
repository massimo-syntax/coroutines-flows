package com.example.core.preferences.model

enum class AppTheme(val displayName: String) {
    SYSTEM("System"),
    AURORA_TEAL("Aurora Teal"),
    SUNSET_CORAL("Sunset Coral"),
    NORDIC_SAPPHIRE("Nordic Sapphire"),
    AURORA_DARK("Aurora Teal Dark"),
    TOKYO_CYAN("Tokyo Cyan"),
    ESPRESSO_GOLD("Espresso Gold");

    val isDark: Boolean
        get() = this in listOf(AURORA_DARK, TOKYO_CYAN, ESPRESSO_GOLD)
}