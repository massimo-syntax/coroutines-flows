package com.example.data.repository

import com.example.data.model.toMyData
import com.example.data.remote.MockApiService
import com.example.domain.model.MyData
import com.example.domain.repository.MyRepository
import jakarta.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val apiService: MockApiService
) : MyRepository {

    override suspend fun getData(): List<MyData> {
        try {
            val data = apiService.getData().toMyData()
            return data
        }catch (e: Exception){
            // send the error further
            error(e.message.toString())
        }
    }
}
