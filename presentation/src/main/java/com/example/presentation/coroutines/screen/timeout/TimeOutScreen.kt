package com.example.presentation.coroutines.screen.timeout

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HourglassEmpty
import androidx.compose.material.icons.rounded.Schedule
import androidx.compose.material.icons.rounded.TimerOff
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TimeoutShowcaseScreen(
    viewModel: TimeoutViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val colors = MaterialTheme.colorScheme
    val maxTimeMs = 4_000f

    val progress = if (state.timeoutMs > 0L) {
        (state.elapsedMs.toFloat() / state.timeoutMs.toFloat()).coerceIn(0f, 1.25f)
    } else 0f

    // Smooth color-coded elapsed timer feedback mapped to MaterialTheme tokens
    val timerColor by animateColorAsState(
        targetValue = when {
            state.result is TimeoutResult.Error -> colors.error
            state.result is TimeoutResult.TimedOutNull -> colors.tertiary
            state.result is TimeoutResult.Success -> colors.secondary
            progress >= 0.95f -> colors.error
            progress >= 0.65f -> colors.tertiary
            else -> colors.primary
        },
        animationSpec = tween(durationMillis = 180),
        label = "elapsedTimerColor"
    )

    val statusText = when {
        state.result is TimeoutResult.Error -> "Timeout Exceeded"
        state.result is TimeoutResult.TimedOutNull -> "Graceful Fallback"
        state.result is TimeoutResult.Success -> "Completed"
        state.result is TimeoutResult.Loading && progress >= 0.65f -> "Approaching Limit"
        state.result is TimeoutResult.Loading -> "Within Budget"
        else -> "Ready"
    }

    val statusIcon = when {
        state.result is TimeoutResult.Error -> Icons.Rounded.TimerOff
        state.result is TimeoutResult.TimedOutNull -> Icons.Rounded.HourglassEmpty
        else -> Icons.Rounded.Schedule
    }

    Scaffold(
        topBar = {
            TimeoutTopBar(onBackPressed = onBackPressed)
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(colors.background)
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Elapsed Time Header Card
            CoroutineElapsedTimeCard(
                state = state,
                timerColor = timerColor,
                statusText = statusText,
                statusIcon = statusIcon
            )

            // 2. Network Delay & Custom Timeout Sliders Card
            TimeoutSlidersCard(
                networkDelayMs = state.networkDelayMs,
                timeoutMs = state.timeoutMs,
                maxTimeMs = maxTimeMs,
                onNetworkDelayChange = viewModel::updateNetworkDelay,
                onTimeoutBudgetChange = viewModel::updateTimeoutBudget
            )

            // 3. Timeout Mode Trigger Buttons
            TimeoutModeSelector(
                activeMode = state.activeMode,
                onRunWithTimeout = viewModel::runWithTimeout,
                onRunWithTimeoutOrNull = viewModel::runWithTimeoutOrNull,
                onRunWithSelectTimeout = viewModel::runWithSelectTimeout
            )

            // 4. Animated Result / Loader Card
            AnimatedContent(
                targetState = state.result,
                transitionSpec = {
                    (fadeIn(tween(240)) +
                            slideInVertically(
                                animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
                                initialOffsetY = { it / 4 }
                            ) +
                            scaleIn(initialScale = 0.96f))
                        .togetherWith(fadeOut(tween(140)))
                        .using(SizeTransform(clip = false))
                },
                label = "timeoutResultTransition"
            ) { result ->
                when (result) {
                    TimeoutResult.Idle -> IdlePromptCard()
                    TimeoutResult.Loading -> CoroutineOrbitalLoader(mode = state.activeMode)
                    is TimeoutResult.Success -> TimeoutResultCard(result = result)
                    is TimeoutResult.TimedOutNull -> TimeoutNullFallbackCard(result = result)
                    is TimeoutResult.Error -> AnimatedTimeoutErrorCard(result = result)
                }
            }
        }
    }
}
