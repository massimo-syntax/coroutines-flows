package com.example.designsystem.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Compare
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun ModalDrawerContent(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    closeDrawer: () -> Unit
){
    ModalDrawerSheet(
        drawerShape = RoundedCornerShape(topEnd = 28.dp, bottomEnd = 28.dp),
        drawerContainerColor = Color(0xFFFAFAFC),
        modifier = Modifier.width(300.dp)
    ) {
        DrawerHeader(
            onClose = closeDrawer
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Navigation Section Title
        Text(
            text = "NAVIGATION",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF79747E),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
        )

        // Profile Item with subtle colored icon background
        NavigationDrawerItem(
            icon = {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFE8DEF8),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.Person, contentDescription = null, tint = Color(0xFF6750A4), modifier = Modifier.size(18.dp))
                    }
                }
            },
            label = { Text("Profile", fontWeight = FontWeight.SemiBold) },
            selected = false,
            onClick = {
                closeDrawer()
                onNavigateToProfile()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        // Settings Item with subtle colored icon background
        NavigationDrawerItem(
            icon = {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFE8DEF8),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.Settings, contentDescription = null, tint = Color(0xFF6750A4), modifier = Modifier.size(18.dp))
                    }
                }
            },
            label = { Text("Settings", fontWeight = FontWeight.SemiBold) },
            selected = false,
            onClick = {
                closeDrawer()
                onNavigateToSettings()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        // Favorites Item with colored background and badge
        NavigationDrawerItem(
            icon = {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFFFD8E4),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = null, tint = Color(0xFFB3261E), modifier = Modifier.size(18.dp))
                    }
                }
            },
            label = { Text("Favorites", fontWeight = FontWeight.SemiBold) },
            badge = {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFFFD8E4)
                ) {
                    Text(
                        text = "6 items",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF31111D),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            },
            selected = false,
            onClick = {
                closeDrawer()
            },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )

        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp))

        // Categories Section Title
        Text(
            text = "CATEGORIES",
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF79747E),
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 6.dp)
        )

        CategoryProgressCard()

        Spacer(modifier = Modifier.weight(1f))

        // Drawer Footer
        DrawerFooter()
    }
}

/**
 * Drawer Header Component with White Border Ring Avatar, Top-Right Close Button & Version Badge
 */
@Composable
fun DrawerHeader(
    onClose: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFE8DEF8))
            .padding(18.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Avatar with White Border Stroke & Elevation Shadow
            Surface(
                shape = CircleShape,
                color = Color(0xFF6750A4),
                border = BorderStroke(2.dp, Color.White),
                shadowElevation = 4.dp,
                modifier = Modifier.size(52.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "JD",
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // Top-Right 'X' Close Button
            IconButton(
                onClick = onClose,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close drawer",
                    tint = Color(0xFF49454F),
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "John Developer",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D192B)
        )
        Text(
            text = "john.dev@android.io",
            style = MaterialTheme.typography.bodySmall,
            color = Color(0xFF49454F)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Colored Badge "Jetpack Compose 1.7"
        Surface(
            shape = RoundedCornerShape(12.dp),
            color = Color(0xFFD0BCFF)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Compare,
                    contentDescription = null,
                    tint = Color(0xFF381E72),
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "Jetpack Compose 1.7",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF381E72)
                )
            }
        }
    }
}

/**
 * Category Progress Overview Card inside Navigation Drawer
 */
@Composable
fun CategoryProgressCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFE0E2EC)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Coroutines", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFF1D1B20))
                Text("3 Items", style = MaterialTheme.typography.labelSmall, color = Color(0xFF79747E))
            }
            Spacer(modifier = Modifier.height(4.dp))
            LinearProgressIndicator(
                progress = { 0.5f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = Color(0xFF0061A4),
                trackColor = Color(0xFFE0E2EC)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Flows", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFF1D1B20))
                Text("3 Items", style = MaterialTheme.typography.labelSmall, color = Color(0xFF79747E))
            }
            Spacer(modifier = Modifier.height(4.dp))

            LinearProgressIndicator(
                progress = { 0.5f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape),
                color = Color(0xFF006A60),
                trackColor = Color(0xFFE0E2EC)
            )
        }
    }
}

/**
 * Navigation Drawer Footer
 */
@Composable
fun DrawerFooter() {
    Surface(
        color = Color(0xFFF3EDF7),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Material You M3", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold, color = Color(0xFF1D1B20))
                Text("Kotlin 2.0 & Coroutines 1.8", style = MaterialTheme.typography.labelSmall, color = Color(0xFF49454F))
            }
            Icon(Icons.Default.Shield, contentDescription = null, tint = Color(0xFF006A60), modifier = Modifier.size(20.dp))
        }
    }
}