package com.example.domain.usecase

import com.example.domain.model.MyData
import com.example.domain.repository.MyRepository
import jakarta.inject.Inject


class GetSomeDataUseCase @Inject constructor(
private val repository: MyRepository
) {

    suspend operator fun invoke(): List<MyData>{
        try{
            return repository.getData()
        } catch (e: Exception) {
            // send to UI
            error(e.message.toString())
        }
    }
}
