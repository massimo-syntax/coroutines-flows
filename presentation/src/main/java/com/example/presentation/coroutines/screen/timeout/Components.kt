package com.example.presentation.coroutines.screen.timeout

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.HourglassEmpty
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.TimerOff
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimeoutTopBar(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Column {
                Text(
                    text = "Timeout Showcases",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "withTimeout · withTimeoutOrNull · select",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BalancedTimeoutSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = valueRange,
        modifier = modifier
            .fillMaxWidth()
            .offset(x = (-8).dp)
            .height(28.dp),
        thumb = {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(colors.secondary)
            )
        },
        track = { sliderState ->
            val span = (sliderState.valueRange.endInclusive - sliderState.valueRange.start).coerceAtLeast(1f)
            val fraction = ((sliderState.value - sliderState.valueRange.start) / span).coerceIn(0f, 1f)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp)
                    .clip(CircleShape)
                    .background(colors.surfaceVariant)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(fraction)
                        .height(6.dp)
                        .clip(CircleShape)
                        .background(colors.secondary)
                )
            }
        }
    )
}

@Composable
fun CoroutineElapsedTimeCard(
    state: TimeoutShowcaseState,
    timerColor: Color,
    statusText: String,
    statusIcon: ImageVector,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = colors.surface,
        border = BorderStroke(1.dp, colors.outlineVariant)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Elapsed Time",
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.onSurfaceVariant
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = statusIcon,
                        contentDescription = statusText,
                        tint = timerColor,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = statusText,
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Medium,
                        color = timerColor
                    )
                }
            }
            Text(
                text = "${state.elapsedMs} ms",
                style = MaterialTheme.typography.displayMedium,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                color = timerColor
            )
            CoroutineRaceTrack(
                elapsedMs = state.elapsedMs,
                timeoutMs = state.timeoutMs,
                networkDelayMs = state.networkDelayMs,
                accentColor = timerColor
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "0 ms",
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    color = colors.onSurfaceVariant
                )
                Text(
                    text = "Net ${state.networkDelayMs} ms · Limit ${state.timeoutMs} ms",
                    style = MaterialTheme.typography.labelSmall,
                    fontFamily = FontFamily.Monospace,
                    color = colors.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun TimeoutSlidersCard(
    networkDelayMs: Long,
    timeoutMs: Long,
    maxTimeMs: Float,
    onNetworkDelayChange: (Long) -> Unit,
    onTimeoutBudgetChange: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = colors.surface,
        border = BorderStroke(1.dp, colors.outlineVariant)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Network Delay Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Network Delay",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSurface,
                    maxLines = 1
                )
                Text(
                    text = "$networkDelayMs ms",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = colors.secondary
                )
                Text(
                    text = "How long the API call needs.",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant
                )
                BalancedTimeoutSlider(
                    value = networkDelayMs.toFloat(),
                    onValueChange = { onNetworkDelayChange(it.toLong()) },
                    valueRange = 100f..maxTimeMs
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf(450L, 1500L).forEach { preset ->
                        val selected = networkDelayMs == preset
                        Surface(
                            onClick = { onNetworkDelayChange(preset) },
                            shape = RoundedCornerShape(8.dp),
                            color = if (selected) colors.secondary else colors.surfaceVariant,
                            contentColor = if (selected) colors.onSecondary else colors.onSurfaceVariant,
                            border = BorderStroke(
                                1.dp,
                                if (selected) colors.secondary else colors.outlineVariant
                            )
                        ) {
                            Text(
                                text = "${preset}ms",
                                style = MaterialTheme.typography.labelSmall,
                                fontFamily = FontFamily.Monospace,
                                maxLines = 1,
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 4.dp
                                )
                            )
                        }
                    }
                }
            }

            // Custom Timeout Column
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Custom Timeout",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSurface,
                    maxLines = 1
                )
                Text(
                    text = "$timeoutMs ms",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Bold,
                    color = colors.secondary
                )
                Text(
                    text = "Max wait before cancel or fallback.",
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurfaceVariant
                )
                BalancedTimeoutSlider(
                    value = timeoutMs.toFloat(),
                    onValueChange = { onTimeoutBudgetChange(it.toLong()) },
                    valueRange = 100f..maxTimeMs
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    listOf(600L, 1200L).forEach { preset ->
                        val selected = timeoutMs == preset
                        Surface(
                            onClick = { onTimeoutBudgetChange(preset) },
                            shape = RoundedCornerShape(8.dp),
                            color = if (selected) colors.secondary else colors.surfaceVariant,
                            contentColor = if (selected) colors.onSecondary else colors.onSurfaceVariant,
                            border = BorderStroke(
                                1.dp,
                                if (selected) colors.secondary else colors.outlineVariant
                            )
                        ) {
                            Text(
                                text = "${preset}ms",
                                style = MaterialTheme.typography.labelSmall,
                                fontFamily = FontFamily.Monospace,
                                maxLines = 1,
                                modifier = Modifier.padding(
                                    horizontal = 8.dp,
                                    vertical = 4.dp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun TimeoutModeSelector(
    activeMode: TimeoutMode,
    onRunWithTimeout: () -> Unit,
    onRunWithTimeoutOrNull: () -> Unit,
    onRunWithSelectTimeout: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        TimeoutMode.entries.forEach { mode ->
            val selected = activeMode == mode
            val onTrigger = when (mode) {
                TimeoutMode.WITH_TIMEOUT_MS -> onRunWithTimeout
                TimeoutMode.WITH_TIMEOUT_OR_NULL_MS -> onRunWithTimeoutOrNull
                TimeoutMode.SELECT_ON_TIMEOUT_MS -> onRunWithSelectTimeout
            }
            FilledTonalButton(
                onClick = onTrigger,
                modifier = Modifier
                    .weight(1f)
                    .height(44.dp),
                contentPadding = PaddingValues(horizontal = 4.dp, vertical = 0.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = if (selected) colors.secondary else colors.outlineVariant
                ),
                colors = ButtonDefaults.filledTonalButtonColors(
                    containerColor = if (selected) colors.secondaryContainer else colors.surface,
                    contentColor = if (selected) colors.onSecondaryContainer else colors.onSurface
                )
            ) {
                Text(
                    text = mode.label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Monospace,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun CoroutineRaceTrack(
    elapsedMs: Long,
    timeoutMs: Long,
    networkDelayMs: Long,
    accentColor: Color
) {
    val colors = MaterialTheme.colorScheme
    val maxDomain = 4_000f // Fixed 4-second overall scale
    val elapsedFraction = (elapsedMs / maxDomain).coerceIn(0f, 1f)
    val timeoutFraction = (timeoutMs / maxDomain).coerceIn(0f, 1f)
    val networkFraction = (networkDelayMs / maxDomain).coerceIn(0f, 1f)

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(14.dp)
    ) {
        val trackHeight = 7.dp.toPx()
        val yOffset = (size.height - trackHeight) / 2f

        drawRoundRect(
            color = colors.surfaceVariant,
            topLeft = Offset(0f, yOffset),
            size = Size(size.width, trackHeight),
            cornerRadius = CornerRadius(trackHeight / 2f)
        )

        drawRoundRect(
            color = colors.secondary.copy(alpha = 0.22f),
            topLeft = Offset(0f, yOffset),
            size = Size(size.width * networkFraction, trackHeight),
            cornerRadius = CornerRadius(trackHeight / 2f)
        )

        drawRoundRect(
            color = accentColor,
            topLeft = Offset(0f, yOffset),
            size = Size(size.width * elapsedFraction, trackHeight),
            cornerRadius = CornerRadius(trackHeight / 2f)
        )

        val deadlineX = size.width * timeoutFraction
        drawLine(
            color = colors.onSurface,
            start = Offset(deadlineX, 0f),
            end = Offset(deadlineX, size.height),
            strokeWidth = 2.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun CoroutineOrbitalLoader(mode: TimeoutMode) {
    val colors = MaterialTheme.colorScheme
    val infinite = rememberInfiniteTransition(label = "orbitalLoader")
    val angle by infinite.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(950, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sweepAngle"
    )
    val iconColor = colors.secondary
    val lightOutline = iconColor.copy(alpha = 0.6f)

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = colors.surface,
        contentColor = colors.onSurface,
        border = BorderStroke(1.dp, lightOutline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.14f))
                    .border(1.dp, lightOutline, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.size(28.dp)) {
                    drawCircle(
                        color = colors.surfaceVariant,
                        style = Stroke(width = 3.dp.toPx())
                    )
                    drawArc(
                        color = iconColor,
                        startAngle = angle,
                        sweepAngle = 240f,
                        useCenter = false,
                        style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Waiting on ${mode.label}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Waiting for API response...",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun TimeoutResultCard(result: TimeoutResult.Success) {
    val colors = MaterialTheme.colorScheme
    val iconColor = colors.secondary
    val lightOutline = iconColor.copy(alpha = 0.6f)

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = colors.secondaryContainer,
        contentColor = colors.onSecondaryContainer,
        border = BorderStroke(1.dp, lightOutline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.16f))
                    .border(1.dp, lightOutline, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Schedule,
                    contentDescription = "Completed in time",
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "${result.mode.label} · Completed",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onSecondaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = result.payload,
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onSecondaryContainer
                )
            }
        }
    }
}

@Composable
fun TimeoutNullFallbackCard(result: TimeoutResult.TimedOutNull) {
    val colors = MaterialTheme.colorScheme
    val translucentErrorBg = colors.errorContainer.copy(alpha = 0.45f)
    val errorContent = colors.onErrorContainer
    val lightOutline = colors.error.copy(alpha = 0.4f)

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = translucentErrorBg,
        contentColor = errorContent,
        border = BorderStroke(1.dp, lightOutline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colors.error.copy(alpha = 0.14f))
                    .border(1.dp, lightOutline, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.HourglassEmpty,
                    contentDescription = "Returned null",
                    tint = colors.error,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Returned null at ${result.elapsedMs} ms",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = errorContent,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${result.mode.label} cancelled gracefully without throwing.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = errorContent
                )
            }
        }
    }
}

@Composable
fun AnimatedTimeoutErrorCard(result: TimeoutResult.Error) {
    val colors = MaterialTheme.colorScheme
    val shakeOffset = remember { Animatable(0f) }
    val translucentErrorBg = colors.errorContainer.copy(alpha = 0.65f)
    val errorContent = colors.onErrorContainer
    val lightOutline = colors.error.copy(alpha = 0.5f)

    LaunchedEffect(result) {
        listOf(-12f, 12f, -8f, 8f, -4f, 0f).forEach { target ->
            shakeOffset.animateTo(target, tween(45, easing = FastOutSlowInEasing))
        }
    }

    Surface(
        modifier = Modifier.graphicsLayer { translationX = shakeOffset.value },
        shape = RoundedCornerShape(16.dp),
        color = translucentErrorBg,
        contentColor = errorContent,
        border = BorderStroke(1.dp, lightOutline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(colors.error.copy(alpha = 0.18f))
                    .border(1.dp, lightOutline, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.TimerOff,
                    contentDescription = "Timeout exception",
                    tint = colors.error,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "TimeoutCancellationException",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = errorContent,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${result.mode.label} aborted at ${result.elapsedMs} ms · ${result.reason}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = errorContent
                )
            }
        }
    }
}

@Composable
fun IdlePromptCard() {
    val colors = MaterialTheme.colorScheme
    val iconColor = colors.primary
    val lightOutline = iconColor.copy(alpha = 0.6f)

    Surface(
        shape = RoundedCornerShape(16.dp),
        color = colors.primaryContainer,
        contentColor = colors.onPrimaryContainer,
        border = BorderStroke(1.dp, lightOutline)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 18.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(iconColor.copy(alpha = 0.16f))
                    .border(1.dp, lightOutline, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Schedule,
                    contentDescription = "Ready",
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = "Ready to Execute",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.onPrimaryContainer,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Pick a delay and timeout above, then tap an operator.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colors.onPrimaryContainer
                )
            }
        }
    }
}
