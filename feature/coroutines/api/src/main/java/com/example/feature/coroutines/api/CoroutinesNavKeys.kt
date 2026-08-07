package com.example.feature.coroutines.api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data object VerySimpleCoroutine : NavKey

@Serializable
data object VeryComplicatedCoroutine : NavKey
