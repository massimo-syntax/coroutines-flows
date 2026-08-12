package com.example.coroutinesflows.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.core.preferences.model.AppTheme


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
    onError = AuroraTealOnError
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
val CyberVioletDarkColorScheme = darkColorScheme(
    primary = CyberVioletPrimary,
    onPrimary = CyberVioletOnPrimary,
    primaryContainer = CyberVioletPrimaryContainer,
    onPrimaryContainer = CyberVioletOnPrimaryContainer,
    secondary = CyberVioletSecondary,
    onSecondary = CyberVioletOnSecondary,
    secondaryContainer = CyberVioletSecondaryContainer,
    onSecondaryContainer = CyberVioletOnSecondaryContainer,
    tertiary = CyberVioletTertiary,
    onTertiary = CyberVioletOnTertiary,
    tertiaryContainer = CyberVioletTertiaryContainer,
    onTertiaryContainer = CyberVioletOnTertiaryContainer,
    background = CyberVioletBackground,
    onBackground = CyberVioletOnBackground,
    surface = CyberVioletSurface,
    onSurface = CyberVioletOnSurface,
    surfaceVariant = CyberVioletSurfaceVariant,
    onSurfaceVariant = CyberVioletOnSurfaceVariant,
    outline = CyberVioletOutline,
    outlineVariant = CyberVioletOutlineVariant,
    error = CyberVioletError,
    onError = CyberVioletOnError
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
    cornerRadius: Int = 16,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.SYSTEM -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme //DarkDefaultColors else LightDefaultColors
        AppTheme.AURORA_TEAL -> AuroraTealLightColorScheme
        AppTheme.SUNSET_CORAL -> SunsetCoralLightColorScheme
        AppTheme.NORDIC_SAPPHIRE -> NordicSapphireLightColorScheme
        AppTheme.CYBER_VIOLET -> CyberVioletDarkColorScheme
        AppTheme.TOKYO_CYAN -> TokyoCyanDarkColorScheme
        AppTheme.ESPRESSO_GOLD -> EspressoGoldDarkColorScheme
    }

    // Best Practice: Dynamically calculate shapes and apply them to MaterialTheme.
    // This allows components like ModernChip to use MaterialTheme.shapes.medium
    // and automatically get the user-selected radius.
    val shapes = Shapes(
        extraSmall = RoundedCornerShape((cornerRadius / 4).dp),
        small = RoundedCornerShape((cornerRadius / 2).dp),
        medium = RoundedCornerShape(cornerRadius.dp),
        large = RoundedCornerShape((cornerRadius * 1.5).toInt().dp),
        extraLarge = RoundedCornerShape((cornerRadius * 2).dp)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = shapes,
        content = content,
    )
}
