package com.example.coroutinesflows.designsystem.theme






import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
//import androidx.compose.material3.ColorScheme
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
//import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext



import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.designsystem.theme.thememodel.AppTheme


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
enum class AppTheme(val displayName: String, val isDark: Boolean) {
    AURORA_TEAL("Aurora Teal", isDark = false),
    SUNSET_CORAL("Sunset Coral", isDark = false),
    NORDIC_SAPPHIRE("Nordic Sapphire", isDark = false),
    CYBER_VIOLET("Cyber Violet", isDark = true),
    TOKYO_CYAN("Tokyo Cyan", isDark = true),
    ESPRESSO_GOLD("Espresso Gold", isDark = true)
}

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

/**
 * Animated ColorScheme wrapper for smooth theme switching transitions
 */
@Composable
fun animate(colorScheme: ColorScheme): ColorScheme {
    val duration = 400
    val spec = tween<Color>(durationMillis = duration)
    return ColorScheme(
        primary = animateColorAsState(colorScheme.primary, animationSpec = spec, label = "p").value,
        onPrimary = animateColorAsState(
            colorScheme.onPrimary,
            animationSpec = spec,
            label = "op"
        ).value,
        primaryContainer = animateColorAsState(
            colorScheme.primaryContainer,
            animationSpec = spec,
            label = "pc"
        ).value,
        onPrimaryContainer = animateColorAsState(
            colorScheme.onPrimaryContainer,
            animationSpec = spec,
            label = "opc"
        ).value,
        inversePrimary = animateColorAsState(
            colorScheme.inversePrimary,
            animationSpec = spec,
            label = "ip"
        ).value,
        secondary = animateColorAsState(
            colorScheme.secondary,
            animationSpec = spec,
            label = "s"
        ).value,
        onSecondary = animateColorAsState(
            colorScheme.onSecondary,
            animationSpec = spec,
            label = "os"
        ).value,
        secondaryContainer = animateColorAsState(
            colorScheme.secondaryContainer,
            animationSpec = spec,
            label = "sc"
        ).value,
        onSecondaryContainer = animateColorAsState(
            colorScheme.onSecondaryContainer,
            animationSpec = spec,
            label = "osc"
        ).value,
        tertiary = animateColorAsState(
            colorScheme.tertiary,
            animationSpec = spec,
            label = "t"
        ).value,
        onTertiary = animateColorAsState(
            colorScheme.onTertiary,
            animationSpec = spec,
            label = "ot"
        ).value,
        tertiaryContainer = animateColorAsState(
            colorScheme.tertiaryContainer,
            animationSpec = spec,
            label = "tc"
        ).value,
        onTertiaryContainer = animateColorAsState(
            colorScheme.onTertiaryContainer,
            animationSpec = spec,
            label = "otc"
        ).value,
        background = animateColorAsState(
            colorScheme.background,
            animationSpec = spec,
            label = "bg"
        ).value,
        onBackground = animateColorAsState(
            colorScheme.onBackground,
            animationSpec = spec,
            label = "obg"
        ).value,
        surface = animateColorAsState(
            colorScheme.surface,
            animationSpec = spec,
            label = "sf"
        ).value,
        onSurface = animateColorAsState(
            colorScheme.onSurface,
            animationSpec = spec,
            label = "osf"
        ).value,
        surfaceVariant = animateColorAsState(
            colorScheme.surfaceVariant,
            animationSpec = spec,
            label = "sv"
        ).value,
        onSurfaceVariant = animateColorAsState(
            colorScheme.onSurfaceVariant,
            animationSpec = spec,
            label = "osv"
        ).value,
        surfaceTint = animateColorAsState(
            colorScheme.surfaceTint,
            animationSpec = spec,
            label = "st"
        ).value,
        inverseSurface = animateColorAsState(
            colorScheme.inverseSurface,
            animationSpec = spec,
            label = "isf"
        ).value,
        inverseOnSurface = animateColorAsState(
            colorScheme.inverseOnSurface,
            animationSpec = spec,
            label = "iosf"
        ).value,
        error = animateColorAsState(colorScheme.error, animationSpec = spec, label = "e").value,
        onError = animateColorAsState(
            colorScheme.onError,
            animationSpec = spec,
            label = "oe"
        ).value,
        errorContainer = animateColorAsState(
            colorScheme.errorContainer,
            animationSpec = spec,
            label = "ec"
        ).value,
        onErrorContainer = animateColorAsState(
            colorScheme.onErrorContainer,
            animationSpec = spec,
            label = "oec"
        ).value,
        outline = animateColorAsState(
            colorScheme.outline,
            animationSpec = spec,
            label = "ol"
        ).value,
        outlineVariant = animateColorAsState(
            colorScheme.outlineVariant,
            animationSpec = spec,
            label = "olv"
        ).value,
        scrim = animateColorAsState(colorScheme.scrim, animationSpec = spec, label = "scrim").value,
    )
}

/*

/**
 * Main Jetpack Compose Theme Composable supporting the 6 Themes & Dynamic Color
 */
@Composable
fun AppTheme(
    selectedTheme: AppTheme = AppTheme.AURORA_TEAL,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val context = LocalContext.current
    val targetColorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            if (selectedTheme.isDark) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> when (selectedTheme) {
            AppTheme.AURORA_TEAL -> AuroraTealLightColorScheme
            AppTheme.SUNSET_CORAL -> SunsetCoralLightColorScheme
            AppTheme.NORDIC_SAPPHIRE -> NordicSapphireLightColorScheme
            AppTheme.CYBER_VIOLET -> CyberVioletDarkColorScheme
            AppTheme.TOKYO_CYAN -> TokyoCyanDarkColorScheme
            AppTheme.ESPRESSO_GOLD -> EspressoGoldDarkColorScheme
        }
    }

    val animatedColorScheme = animate(targetColorScheme)

    MaterialTheme(
        colorScheme = animatedColorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

// SHAPES

package com.example.app.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp)
)










 */












































@Composable
fun CoroutinesFlowsTheme(
    appTheme: AppTheme,
    content: @Composable () -> Unit
) {
    val colorScheme = when (appTheme) {
        AppTheme.SYSTEM -> if (isSystemInDarkTheme()) DarkColorScheme else LightColorScheme //DarkDefaultColors else LightDefaultColors
        AppTheme.LIGHT_DEFAULT -> LightColorScheme//LightDefaultColors
        AppTheme.LIGHT_OCEAN -> LightColorScheme//LightOceanColors
        AppTheme.LIGHT_SUNSET -> LightColorScheme//LightSunsetColors
        AppTheme.DARK_DEFAULT -> DarkColorScheme//DarkDefaultColors
        AppTheme.DARK_MIDNIGHT -> DarkColorScheme//DarkMidnightColors
        AppTheme.DARK_FOREST -> DarkColorScheme//DarkForestColors
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}