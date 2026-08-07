package com.example.data.remote

import com.example.data.model.SomeNetworkDataDto
import retrofit2.http.GET

interface MockApiService {
    @GET("data")
    suspend fun getData(): List<SomeNetworkDataDto>

    @GET("other")
    suspend fun getOther(): List<SomeNetworkDataDto>

}