package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import retrofit2.http.GET

interface MockApiService {
    @GET(DATA)
    suspend fun getData(): List<SomeNetworkDataDto>

    @GET(ERROR)
    suspend fun getError(): List<SomeNetworkDataDto>

    @GET("other")
    suspend fun getOther(): List<SomeNetworkDataDto>
}
