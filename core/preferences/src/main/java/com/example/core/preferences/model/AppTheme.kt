package com.example.core.preferences.model

enum class AppTheme(val displayName: String) {
    SYSTEM("System"),
    AURORA_TEAL("Aurora Teal"),
    SUNSET_CORAL("Sunset Coral"),
    NORDIC_SAPPHIRE("Nordic Sapphire"),
    CYBER_VIOLET("Cyber Violet"),
    TOKYO_CYAN("Tokyo Cyan"),
    ESPRESSO_GOLD("Espresso Gold");

    val isDark: Boolean
        get() = this in listOf(CYBER_VIOLET, TOKYO_CYAN, ESPRESSO_GOLD)
}
