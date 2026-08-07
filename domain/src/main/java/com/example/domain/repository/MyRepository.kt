package com.example.domain.repository

import com.example.domain.model.MyData

interface MyRepository {
    suspend fun getData(): List<MyData>
}