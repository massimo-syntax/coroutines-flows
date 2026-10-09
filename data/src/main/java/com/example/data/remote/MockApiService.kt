package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import retrofit2.http.GET

interface MockApiService {
    @GET(DATA)
    suspend fun getData(): List<SomeNetworkDataDto>

    @GET("other")
    suspend fun getOther(): List<SomeNetworkDataDto>

    @GET(SEQUENTIAL_GET_NEWEST_ANDROID_VERSIONS)
    suspend fun getSequentialNewestAndroidVersions(): List<SomeNetworkDataDto>

    @GET(SEQUENTIAL_GET_FEATURES_NEWEST_ANDROID_VERSIONS)
    suspend fun getSequentialFeaturesNewestAndroidVersions(): List<SomeNetworkDataDto>


    @GET(ERROR)
    suspend fun getError(): List<SomeNetworkDataDto>
}
