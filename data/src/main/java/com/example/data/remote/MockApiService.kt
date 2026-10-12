package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import com.example.data.remote.server.ADD_AGENT_SKILLS_COROUTINES
import com.example.data.remote.server.DATA
import com.example.data.remote.server.ERROR
import com.example.data.remote.server.SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS
import com.example.data.remote.server.SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS
import com.example.domain.model.AndroidFeature
import retrofit2.http.GET

interface MockApiService {
    @GET(DATA)
    suspend fun getData(): List<SomeNetworkDataDto>

    @GET("other")
    suspend fun getOther(): List<SomeNetworkDataDto>

    @GET(SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS)
    suspend fun getSequentialNewestAndroidVersions(): List<SomeNetworkDataDto>

    @GET(SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS)
    suspend fun getSequentialFeaturesNewestAndroidVersions(): List<AndroidFeature>

    @GET(ADD_AGENT_SKILLS_COROUTINES)
    suspend fun addAgentSkills(): List<SomeNetworkDataDto>


    @GET(ERROR)
    suspend fun getError(): List<SomeNetworkDataDto>
}
