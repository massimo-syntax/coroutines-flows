package com.example.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import com.example.core.preferences.model.AppTheme
import com.example.coroutinesflows.designsystem.theme.Typography


private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

/**
 * 6 Distinct Jetpack Compose Themes (3 Light & 3 Dark)
 */


// -------------------------------------------------------------
// 1. Light Schemes
// -------------------------------------------------------------
val AuroraTealLightColorScheme = lightColorScheme(
    primary = AuroraTealPrimary,
    onPrimary = AuroraTealOnPrimary,
    primaryContainer = AuroraTealPrimaryContainer,
    onPrimaryContainer = AuroraTealOnPrimaryContainer,
    secondary = AuroraTealSecondary,
    onSecondary = AuroraTealOnSecondary,
    secondaryContainer = AuroraTealSecondaryContainer,
    onSecondaryContainer = AuroraTealOnSecondaryContainer,
    tertiary = AuroraTealTertiary,
    onTertiary = AuroraTealOnTertiary,
    tertiaryContainer = AuroraTealTertiaryContainer,
    onTertiaryContainer = AuroraTealOnTertiaryContainer,
    background = AuroraTealBackground,
    onBackground = AuroraTealOnBackground,
    surface = AuroraTealSurface,
    onSurface = AuroraTealOnSurface,
    surfaceVariant = AuroraTealSurfaceVariant,
    onSurfaceVariant = AuroraTealOnSurfaceVariant,
    outline = AuroraTealOutline,
    outlineVariant = AuroraTealOutlineVariant,
    error = AuroraTealError,
    onError = AuroraTealOnError,
)

val SunsetCoralLightColorScheme = lightColorScheme(
    primary = SunsetCoralPrimary,
    onPrimary = SunsetCoralOnPrimary,
    primaryContainer = SunsetCoralPrimaryContainer,
    onPrimaryContainer = SunsetCoralOnPrimaryContainer,
    secondary = SunsetCoralSecondary,
    onSecondary = SunsetCoralOnSecondary,
    secondaryContainer = SunsetCoralSecondaryContainer,
    onSecondaryContainer = SunsetCoralOnSecondaryContainer,
    tertiary = SunsetCoralTertiary,
    onTertiary = SunsetCoralOnTertiary,
    tertiaryContainer = SunsetCoralTertiaryContainer,
    onTertiaryContainer = SunsetCoralOnTertiaryContainer,
    background = SunsetCoralBackground,
    onBackground = SunsetCoralOnBackground,
    surface = SunsetCoralSurface,
    onSurface = SunsetCoralOnSurface,
    surfaceVariant = SunsetCoralSurfaceVariant,
    onSurfaceVariant = SunsetCoralOnSurfaceVariant,
    outline = SunsetCoralOutline,
    outlineVariant = SunsetCoralOutlineVariant,
    error = SunsetCoralError,
    onError = SunsetCoralOnError
)

val NordicSapphireLightColorScheme = lightColorScheme(
    primary = NordicSapphirePrimary,
    onPrimary = NordicSapphireOnPrimary,
    primaryContainer = NordicSapphirePrimaryContainer,
    onPrimaryContainer = NordicSapphireOnPrimaryContainer,
    secondary = NordicSapphireSecondary,
    onSecondary = NordicSapphireOnSecondary,
    secondaryContainer = NordicSapphireSecondaryContainer,
    onSecondaryContainer = NordicSapphireOnSecondaryContainer,
    tertiary = NordicSapphireTertiary,
    onTertiary = NordicSapphireOnTertiary,
    tertiaryContainer = NordicSapphireTertiaryContainer,
    onTertiaryContainer = NordicSapphireOnTertiaryContainer,
    background = NordicSapphireBackground,
    onBackground = NordicSapphireOnBackground,
    surface = NordicSapphireSurface,
    onSurface = NordicSapphireOnSurface,
    surfaceVariant = NordicSapphireSurfaceVariant,
    onSurfaceVariant = NordicSapphireOnSurfaceVariant,
    outline = NordicSapphireOutline,
    outlineVariant = NordicSapphireOutlineVariant,
    error = NordicSapphireError,
    onError = NordicSapphireOnError
)

// -------------------------------------------------------------
// 2. Dark Schemes
// -------------------------------------------------------------
val AuroraTealDarkColorScheme = darkColorScheme(
    primary = AuroraTealDarkPrimary,
    onPrimary = AuroraTealDarkOnPrimary,
    primaryContainer = AuroraTealDarkPrimaryContainer,
    onPrimaryContainer = AuroraTealDarkOnPrimaryContainer,
    secondary = AuroraTealDarkSecondary,
    onSecondary = AuroraTealDarkOnSecondary,
    secondaryContainer = AuroraTealDarkSecondaryContainer,
    onSecondaryContainer = AuroraTealDarkOnSecondaryContainer,
    tertiary = AuroraTealDarkTertiary,
    onTertiary = AuroraTealDarkOnTertiary,
    tertiaryContainer = AuroraTealDarkTertiaryContainer,
    onTertiaryContainer = AuroraTealDarkOnTertiaryContainer,
    background = AuroraTealDarkBackground,
    onBackground = AuroraTealDarkOnBackground,
    surface = AuroraTealDarkSurface,
    onSurface = AuroraTealDarkOnSurface,
    surfaceVariant = AuroraTealDarkSurfaceVariant,
    onSurfaceVariant = AuroraTealDarkOnSurfaceVariant,
    outline = AuroraTealDarkOutline,
    outlineVariant = AuroraTealDarkOutlineVariant,
    error = AuroraTealDarkError,
    onError = AuroraTealDarkOnError
)

val TokyoCyanDarkColorScheme = darkColorScheme(
    primary = TokyoCyanPrimary,
    onPrimary = TokyoCyanOnPrimary,
    primaryContainer = TokyoCyanPrimaryContainer,
    onPrimaryContainer = TokyoCyanOnPrimaryContainer,
    secondary = TokyoCyanSecondary,
    onSecondary = TokyoCyanOnSecondary,
    secondaryContainer = TokyoCyanSecondaryContainer,
    onSecondaryContainer = TokyoCyanOnSecondaryContainer,
    tertiary = TokyoCyanTertiary,
    onTertiary = TokyoCyanOnTertiary,
    tertiaryContainer = TokyoCyanTertiaryContainer,
    onTertiaryContainer = TokyoCyanOnTertiaryContainer,
    background = TokyoCyanBackground,
    onBackground = TokyoCyanOnBackground,
    surface = TokyoCyanSurface,
    onSurface = TokyoCyanOnSurface,
    surfaceVariant = TokyoCyanSurfaceVariant,
    onSurfaceVariant = TokyoCyanOnSurfaceVariant,
    outline = TokyoCyanOutline,
    outlineVariant = TokyoCyanOutlineVariant,
    error = TokyoCyanError,
    onError = TokyoCyanOnError
)

val EspressoGoldDarkColorScheme = darkColorScheme(
    primary = EspressoGoldPrimary,
    onPrimary = EspressoGoldOnPrimary,
    primaryContainer = EspressoGoldPrimaryContainer,
    onPrimaryContainer = EspressoGoldOnPrimaryContainer,
    secondary = EspressoGoldSecondary,
    onSecondary = EspressoGoldOnSecondary,
    secondaryContainer = EspressoGoldSecondaryContainer,
    onSecondaryContainer = EspressoGoldOnSecondaryContainer,
    tertiary = EspressoGoldTertiary,
    onTertiary = EspressoGoldOnTertiary,
    tertiaryContainer = EspressoGoldTertiaryContainer,
    onTertiaryContainer = EspressoGoldOnTertiaryContainer,
    background = EspressoGoldBackground,
    onBackground = EspressoGoldOnBackground,
    surface = EspressoGoldSurface,
    onSurface = EspressoGoldOnSurface,
    surfaceVariant = EspressoGoldSurfaceVariant,
    onSurfaceVariant = EspressoGoldOnSurfaceVariant,
    outline = EspressoGoldOutline,
    outlineVariant = EspressoGoldOutlineVariant,
    error = EspressoGoldError,
    onError = EspressoGoldOnError
)


@Composable
fun CoroutinesFlowsTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.SYSTEM -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme //DarkDefaultColors else LightDefaultColors
        AppTheme.AURORA_TEAL -> AuroraTealLightColorScheme
        AppTheme.SUNSET_CORAL -> SunsetCoralLightColorScheme
        AppTheme.NORDIC_SAPPHIRE -> NordicSapphireLightColorScheme
        AppTheme.AURORA_DARK -> AuroraTealDarkColorScheme
        AppTheme.TOKYO_CYAN -> TokyoCyanDarkColorScheme
        AppTheme.ESPRESSO_GOLD -> EspressoGoldDarkColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
