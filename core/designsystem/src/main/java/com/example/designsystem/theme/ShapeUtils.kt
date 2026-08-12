package com.example.designsystem.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

/**
 * Utility to convert an integer radius value into a Material 3 [CornerBasedShape].
 * It maps specific values to the Theme's shape tokens for consistency, 
 * or falls back to a custom [RoundedCornerShape].
 */
@Composable
fun Int.asCornerShape(): CornerBasedShape {
    return when (this) {
        4 -> RoundedCornerShape(4.dp)
        8 -> RoundedCornerShape(8.dp)
        20 -> MaterialTheme.shapes.medium
        else -> RoundedCornerShape(this.dp)
    }
}

/**
 * Factory function to get the current selected corner style based on a radius value.
 * Usage: val shape = SelectedCorners(appState.cornerRadiusValue)
 */
@Composable
fun SelectedCorners(radius: Int): CornerBasedShape = radius.asCornerShape()
