package com.example.designsystem.components

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModernAppBar(
    isDrawerOpen: Boolean,
    onToggleDrawer: () -> Unit,
    onProfileClick: () -> Unit
) {
    val rotationAngle by animateFloatAsState(
        targetValue = if (isDrawerOpen) 180f else 0f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
    )

    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Concurrency & Flows",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )
                Text(
                    text = "Jetpack Compose Hub",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF49454F)
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onToggleDrawer) {
                Icon(
                    imageVector = if (isDrawerOpen) Icons.Default.Close else Icons.Default.Menu,
                    contentDescription = "Toggle Drawer",
                    modifier = Modifier.rotate(rotationAngle),
                    tint = Color(0xFF1D1B20)
                )
            }
        },
        actions = {
            IconButton(onClick = onProfileClick) {
                Surface(
                    shape = CircleShape,
                    color = Color(0xFFE8DEF8),
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = "JD",
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1D192B)
                        )
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFF8F9FA)
        )
    )
}