package com.example.presentation.coroutines.screen.timeout

enum class TimeoutMode(val label: String) {
    WITH_TIMEOUT_MS("withTimeout"),
    WITH_TIMEOUT_OR_NULL_MS("withTimeoutOrNull"),
    SELECT_ON_TIMEOUT_MS("select { onTimeout }")
}

sealed interface TimeoutResult {
    data object Idle : TimeoutResult
    data object Loading : TimeoutResult
    data class Success(
        val payload: String,
        val elapsedMs: Long,
        val mode: TimeoutMode
    ) : TimeoutResult
    data class TimedOutNull(
        val elapsedMs: Long,
        val mode: TimeoutMode
    ) : TimeoutResult
    data class Error(
        val reason: String,
        val elapsedMs: Long,
        val mode: TimeoutMode
    ) : TimeoutResult
}

data class TimeoutShowcaseState(
    val networkDelayMs: Long = 1800L,
    val timeoutMs: Long = 1200L,
    val elapsedMs: Long = 0L,
    val activeMode: TimeoutMode = TimeoutMode.WITH_TIMEOUT_MS,
    val result: TimeoutResult = TimeoutResult.Idle
)