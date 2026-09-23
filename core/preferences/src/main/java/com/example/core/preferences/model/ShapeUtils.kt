package com.example.designsystem.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun Int.asCornerShape(): CornerBasedShape {
    return when (this) {
        CornerShape.SMALL.value -> RoundedCornerShape(4.dp)
        CornerShape.MEDIUM.value -> RoundedCornerShape(8.dp)
        CornerShape.LARGE.value -> MaterialTheme.shapes.medium
        else -> RoundedCornerShape(this.dp)
    }
}

enum class CornerShape(val value: Int){
    SMALL(4),
    MEDIUM(8),
    LARGE(20)
}