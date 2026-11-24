package com.example.presentation.coroutines.screen.exponentialretry

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel

@Composable
fun NetworkRetryScreen(
    viewModel: NetworkViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    // Collects StateFlow from ViewModel; UI recomposes automatically on every update
    val state by viewModel.uiState.collectAsState()

    // Remembered list inside the screen that records each attempt as ViewModel state updates
    val attemptHistory = remember { mutableStateListOf<AttemptEntry>() }

    LaunchedEffect(state.attempts, state.isSuccess, state.isRunning) {
        if (state.attempts == 0) {
            attemptHistory.clear()
        } else if (state.isSuccess != null && attemptHistory.none { it.attempt == state.attempts }) {
            val detail = if (state.isSuccess == true) {
                "Network block returned T"
            } else {
                val backoffSeconds = if (state.attempts == 1) "1.0s" else "2.0s"
                "HTTP 503 Service Unavailable (failure ${state.attempts}) · Backoff $backoffSeconds"
            }
            attemptHistory.add(
                AttemptEntry(
                    attempt = state.attempts,
                    isSuccess = state.isSuccess == true,
                    detail = detail
                )
            )
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            NetworkRetryTopBar(onBackPressed = onBackPressed)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Elapsed Timer Card
            ElapsedTimeCard(state = state)

            // 2. Animated Status Card
            StatusCard(state = state)

            // 3. Retry Sequence List
            RetrySequenceList(attemptHistory = attemptHistory)

            Spacer(modifier = Modifier.weight(1f))

            // 4. Primary Button: starts the timer and triggers the 3-attempt retry flow
            Button(
                onClick = { viewModel.performNetworkRequest() },
                enabled = !state.isRunning,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = when {
                        state.isRunning -> "Retrying Request..."
                        state.isSuccess == true -> "Run Request Again"
                        else -> "Start Network Request"
                    },
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}
