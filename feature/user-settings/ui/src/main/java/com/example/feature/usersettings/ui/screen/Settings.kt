package com.example.feature.usersettings.ui.screen

import android.widget.Toast
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.coroutinesflows.LocalAppState
import com.example.designsystem.theme.asCornerShape
import com.example.coroutinesflows.designsystem.theme.AuroraTealOnSurface
import com.example.coroutinesflows.designsystem.theme.AuroraTealPrimary
import com.example.coroutinesflows.designsystem.theme.AuroraTealSecondary
import com.example.coroutinesflows.designsystem.theme.AuroraTealSurface
import com.example.coroutinesflows.designsystem.theme.AuroraTealTertiary
import com.example.coroutinesflows.designsystem.theme.CyberVioletOnSurface
import com.example.coroutinesflows.designsystem.theme.CyberVioletPrimary
import com.example.coroutinesflows.designsystem.theme.CyberVioletSecondary
import com.example.coroutinesflows.designsystem.theme.CyberVioletSurface
import com.example.coroutinesflows.designsystem.theme.CyberVioletTertiary
import com.example.coroutinesflows.designsystem.theme.EspressoGoldOnSurface
import com.example.coroutinesflows.designsystem.theme.EspressoGoldPrimary
import com.example.coroutinesflows.designsystem.theme.EspressoGoldSecondary
import com.example.coroutinesflows.designsystem.theme.EspressoGoldSurface
import com.example.coroutinesflows.designsystem.theme.EspressoGoldTertiary
import com.example.coroutinesflows.designsystem.theme.NordicSapphireOnSurface
import com.example.coroutinesflows.designsystem.theme.NordicSapphirePrimary
import com.example.coroutinesflows.designsystem.theme.NordicSapphireSecondary
import com.example.coroutinesflows.designsystem.theme.NordicSapphireSurface
import com.example.coroutinesflows.designsystem.theme.NordicSapphireTertiary
import com.example.coroutinesflows.designsystem.theme.SunsetCoralOnSurface
import com.example.coroutinesflows.designsystem.theme.SunsetCoralPrimary
import com.example.coroutinesflows.designsystem.theme.SunsetCoralSecondary
import com.example.coroutinesflows.designsystem.theme.SunsetCoralSurface
import com.example.coroutinesflows.designsystem.theme.SunsetCoralTertiary
import com.example.coroutinesflows.designsystem.theme.TokyoCyanOnSurface
import com.example.coroutinesflows.designsystem.theme.TokyoCyanPrimary
import com.example.coroutinesflows.designsystem.theme.TokyoCyanSecondary
import com.example.coroutinesflows.designsystem.theme.TokyoCyanSurface
import com.example.coroutinesflows.designsystem.theme.TokyoCyanTertiary
import com.example.core.preferences.model.AppTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {

    val appState = LocalAppState.current

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val selectedCornerStyle = appState.cornerRadiusValue.asCornerShape()


    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {

            // APP BAR

            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primaryContainer),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "JC",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Column {
                            Text(
                                text = "Theme Settings",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = appState.currentTheme.displayName,
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        containerColor =MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Screen Title
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Settings & Theme",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Jetpack Compose Material 3 Dynamic System",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primaryContainer),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.AutoAwesome,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            // SELECT THEME SECTION
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Palette,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = "Select theme",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary,
                        )
                    }
                    Spacer(Modifier.width(8.dp))
                    AssistChip(
                        onClick = {},
                        label = { Text(appState.currentTheme.displayName, style = MaterialTheme.typography.labelSmall) },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            labelColor = MaterialTheme.colorScheme.onSecondaryContainer
                        ),
                        border = AssistChipDefaults.assistChipBorder(enabled = true, borderColor = MaterialTheme.colorScheme.outlineVariant),
                        shape = selectedCornerStyle,
                    )
                }

                // Sub-section: Light Modes
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = Color(0xFFF59E0B),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Light Modes",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppTheme.entries.filter { !it.isDark }.forEach { theme ->
                        // skip system
                        if(theme == AppTheme.SYSTEM) return@forEach
                        Box(modifier = Modifier.weight(1f)) {
                            ThemePaletteCard(
                                theme = theme,
                                isSelected = theme == appState.currentTheme,
                                cornerShape = selectedCornerStyle,
                                onClick = {
                                    appState.setTheme(theme)
                                    //onThemeSelected(theme)
                                    scope.launch {
                                        //snackbarHostState.showSnackbar("Switched to Light Theme: ${theme.name}")
                                    }
                                }
                            )
                        }
                    }
                }

                // Sub-section: Dark Modes
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = null,
                        tint = Color(0xFF818CF8),
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Dark Modes",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AppTheme.entries.filter { it.isDark }.forEach { theme ->
                        Box(modifier = Modifier.weight(1f)) {
                            ThemePaletteCard(
                                theme = theme,
                                isSelected = theme == appState.currentTheme,
                                cornerShape = selectedCornerStyle,
                                onClick = {
                                    appState.setTheme(theme)
//                                    onThemeSelected(theme)
                                    scope.launch {
                                        //snackbarHostState.showSnackbar("Switched to Dark Theme: ${theme.name}")
                                    }
                                }
                            )
                        }
                    }
                }
            }

            // SELECT CORNER RADIUS SECTION
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Tune,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "CORNERS CUSTOMIZATION",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = selectedCornerStyle,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // short feature description -corner radius preference-
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = "Shapes used from your app",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Select how the corners of your elements would be displayed ",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                        }
                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Corner Radius Style",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Your shapes",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }

                            // Select corner radius on click

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                val cornerStyles = listOf(
                                    Triple(4, "Small", "Sharp corners"),
                                    Triple(8, "Medium", "Semi rounded"),
                                    Triple(20, "Large", "More rounded")
                                )

                                cornerStyles.forEach { (radius, name, description) ->
                                    val isSelected = appState.cornerRadiusValue == radius
                                    Box(modifier = Modifier.weight(1f)) {
                                        Card(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    appState.setCornerRadius(radius)
                                                    scope.launch {
                                                        //snackbarHostState.showSnackbar("Corner Style: $description", duration = SnackbarDuration.Short)
                                                    }
                                                },
                                            shape = RoundedCornerShape(radius.dp),
                                            elevation = CardDefaults.cardElevation(
                                                defaultElevation = 4.dp
                                            ),
                                            border = androidx.compose.foundation.BorderStroke(
                                                width = if (isSelected) 2.dp else 1.dp,
                                                color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
                                            ),
                                            colors = CardDefaults.cardColors(
                                                containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
                                            )
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(10.dp),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.spacedBy(6.dp)
                                            ) {
                                                // Visual Shape Thumbnail Box
                                                Box(
                                                    modifier = Modifier
                                                        .size(38.dp, 22.dp)
                                                        .clip(RoundedCornerShape(radius))
                                                        .background(
                                                            if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.25f) else Color.Transparent
                                                        )
                                                        .border(
                                                            width = 2.dp,
                                                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outline,
                                                            shape = RoundedCornerShape(radius)
                                                        ),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Box(
                                                        modifier = Modifier
                                                            .size(14.dp, 6.dp)
                                                            .clip(RoundedCornerShape(1.dp))
                                                            .background(if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant)
                                                    )
                                                }

                                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                                    Text(
                                                        text = name,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        fontWeight = FontWeight.Bold,
                                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                                    )
                                                    Text(
                                                        text = description,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        maxLines = 1,
                                                        fontSize = 9.sp,
                                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Device Preferences: notifications, haptic feedback
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "DEVICE PREFERENCES",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        contentColor = MaterialTheme.colorScheme.onSurface
                    ),
                    shape = selectedCornerStyle,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        // Push Notifications Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(selectedCornerStyle)
                                        .background(MaterialTheme.colorScheme.secondaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Notifications,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "Push Notifications",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "System updates & theme alerts",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = appState.areNotificationsEnabled,
                                onCheckedChange = {
                                    appState.setNotificationsEnabled(it)
                                    scope.launch {
                                        //snackbarHostState.showSnackbar(if (it) "Notifications enabled" else "Notifications muted")
                                    }
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                                    checkedTrackColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }

                        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)

                        // Haptic Feedback Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(selectedCornerStyle)
                                        .background(MaterialTheme.colorScheme.tertiaryContainer),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.TouchApp,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.onTertiaryContainer,
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                                Column {
                                    Text(
                                        text = "Haptic Feedback",
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = FontWeight.Medium,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = "Touch vibrations on click",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }
                            Switch(
                                checked = appState.areHapticsEnabled,
                                onCheckedChange = {
                                    appState.setHapticsEnabled(it)
                                    scope.launch {
                                        snackbarHostState.showSnackbar(if (it) "Haptics enabled" else "Haptics disabled")
                                    }
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                                    checkedTrackColor = MaterialTheme.colorScheme.primary
                                )
                            )
                        }
                    }
                }
            }

            // Toast Test Button
            Button(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Theme Active: ${appState.currentTheme.name} (${if (appState.currentTheme.isDark) "DARK" else "LIGHT"})")
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = selectedCornerStyle,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.AutoAwesome,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Test Theme Toast Message",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}




val themeColors: Map<AppTheme, List<androidx.compose.ui.graphics.Color>> = mapOf(
    AppTheme.AURORA_TEAL to listOf(
        AuroraTealPrimary,
        AuroraTealSecondary,
        AuroraTealTertiary,
        AuroraTealSurface,
        AuroraTealOnSurface
    ),

    AppTheme.SUNSET_CORAL to listOf(
        SunsetCoralPrimary,
        SunsetCoralSecondary,
        SunsetCoralTertiary,
        SunsetCoralSurface,
        SunsetCoralOnSurface
    ),

    AppTheme.NORDIC_SAPPHIRE to listOf(
        NordicSapphirePrimary,
        NordicSapphireSecondary,
        NordicSapphireTertiary,
        NordicSapphireSurface,
        NordicSapphireOnSurface
    ),

    AppTheme.CYBER_VIOLET to listOf(
        CyberVioletPrimary,
        CyberVioletSecondary,
        CyberVioletTertiary,
        CyberVioletSurface,
        CyberVioletOnSurface
    ),

    AppTheme.TOKYO_CYAN to listOf(
        TokyoCyanPrimary,
        TokyoCyanSecondary,
        TokyoCyanTertiary,
        TokyoCyanSurface,
        TokyoCyanOnSurface
    ),

    AppTheme.ESPRESSO_GOLD to listOf(
        EspressoGoldPrimary,
        EspressoGoldSecondary,
        EspressoGoldTertiary,
        EspressoGoldSurface,
        EspressoGoldOnSurface
    )
)




@Composable
private fun ThemePaletteCard(
    theme: AppTheme,
    isSelected: Boolean,
    cornerShape: CornerBasedShape,
    onClick: () -> Unit
) {

    val colors = themeColors[theme] ?: listOf(Color.Blue,Color.Red,Color.Cyan)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = cornerShape,
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 2.dp else 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        ),
        colors = CardDefaults.cardColors(
            containerColor = colors[3]
        )
    ) {
        Box(modifier = Modifier.padding(10.dp)) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                // Color Swatch Dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(CircleShape)
                            .background(colors[0])
                    )
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(colors[1])
                    )
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(colors[2])
                    )
                }

                Text(
                    text = theme.displayName,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = colors[4],
                    maxLines = 2,
                    minLines = 2 // so the card are all high he same
                )

                Text(
                    text = if (theme.isDark) "Dark Mode" else "Light Mode",
                    style = MaterialTheme.typography.labelSmall,
                    color = colors[4].copy(alpha = .8f),
                    maxLines = 1,
                    fontSize = 9.sp
                )
            }

            if (isSelected) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Selected",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(10.dp)
                    )
                }
            }
        }
    }
}
