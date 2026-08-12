package com.example.designsystem.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.coroutinesflows.LocalAppState
import com.example.designsystem.theme.asCornerShape
import androidx.compose.ui.unit.sp


data class ChipData(
    val label: String,
    val icon: ImageVector
)

@Composable
fun ModernChip(
    chipData: ChipData,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val appState = LocalAppState.current
    val shape = appState.cornerRadiusValue.asCornerShape()

    val transition = updateTransition(
        targetState = selected,
        label = "chipTransition"
    )

    val backgroundColor by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 150,
                easing = EaseInOutBounce
            )
        },
        label = "background"
    ) { isSelected ->
        if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.surfaceContainer
        }
    }

    val contentColor by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 200,
                easing = EaseInExpo
            )
        },
        label = "content"
    ) { isSelected ->

        if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        }
    }

    val iconColor by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 200,
                easing = EaseInExpo
            )
        },
        label = "content"
    ) { isSelected ->
        if (isSelected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.primary
        }
    }


    val borderColor by transition.animateColor(
        transitionSpec = {
            tween(
                durationMillis = 100,
                easing = FastOutSlowInEasing
            )
        },
        label = "border"
    ) { isSelected ->
        if (isSelected) {
            Color.Transparent
        } else {
            MaterialTheme.colorScheme.outlineVariant
        }
    }


    val scale by transition.animateFloat(
        transitionSpec = {
            spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessHigh,
            )
        },
        label = "scale"
    ) { isSelected ->
        if (isSelected) 1.02f else 1f
    }

    Surface(
        onClick = onClick,
        modifier = modifier
            .scale(scale),
        shape = shape,
        color = backgroundColor,
        border = BorderStroke(
            width = 1.dp,
            color = borderColor
        ),
    ) {
        Row(
            modifier = Modifier
                .padding(
                    horizontal = 8.dp,
                    vertical = 4.dp
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Icon(
                imageVector = chipData.icon,
                contentDescription = null,
                modifier = Modifier.size(19.dp),
                tint = iconColor
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = chipData.label,
                color = contentColor,
                fontSize = 14.sp,
                fontWeight = if (selected) {
                    FontWeight.Bold
                } else {
                    FontWeight.Light
                },
                maxLines = 1
            )
        }
    }
}