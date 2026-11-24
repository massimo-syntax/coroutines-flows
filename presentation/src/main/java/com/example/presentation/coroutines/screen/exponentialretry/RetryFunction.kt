package com.example.presentation.coroutines.screen.exponentialretry


import kotlinx.coroutines.delay

/**
 * Tries up to [times] (default 3) network requests with exponential backoff.
 * [onRetryUpdate] is the callback used to notify the UI after each attempt.
 */
suspend fun <T> retryWithExponentialBackoff(
    times: Int = 3,
    initialDelayMillis: Long = 1000L,
    factor: Double = 2.0,
    onRetryUpdate: (attempt: Int, isSuccess: Boolean) -> Unit = { _, _ -> },
    block: suspend () -> T
): T {
    var currentDelay = initialDelayMillis

    // Try up to 3 network requests; each catch notifies the UI and waits longer
    for (attempt in 1..times) {
        try {
            val result = block()
            onRetryUpdate(attempt, true) // Notify UI that this attempt succeeded
            return result
        } catch (e: Exception) {
            onRetryUpdate(attempt, false) // Notify UI of failure 1 or failure 2
            if (attempt == times) throw e
            delay(currentDelay)
            currentDelay = (currentDelay * factor).toLong() // Exponential backoff (1s -> 2s)
        }
    }

    error("Unreachable")
}