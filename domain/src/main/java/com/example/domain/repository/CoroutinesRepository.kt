package com.example.domain.repository

import com.example.domain.model.AndroidFeature
import com.example.domain.model.MyData

interface CoroutinesRepository {
    suspend fun getData(): List<MyData>
    suspend fun getSequentialNewestAndroidVersions(): List<MyData>
    suspend fun getSequentialFeaturesNewestAndroidVersions(): List<AndroidFeature>
    suspend fun addAgentSkills(): List<MyData>
    suspend fun getError()
    suspend fun delayTimeout(delayMs: Long): String
}
