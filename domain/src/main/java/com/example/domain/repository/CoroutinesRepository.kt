package com.example.domain.repository

import com.example.domain.model.MyData

interface CoroutinesRepository {
    suspend fun getData(): List<MyData>
    suspend fun getSequentialNewestAndroidVersions(): List<MyData>
    suspend fun getSequentialFeaturesNewestAndroidVersions(): List<MyData>
    suspend fun getError()
}