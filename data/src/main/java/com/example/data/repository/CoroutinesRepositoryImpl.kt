package com.example.data.repository

import com.example.data.model.toMyData
import com.example.data.remote.MockApiService
import com.example.domain.model.MyData
import com.example.domain.repository.CoroutinesRepository
import jakarta.inject.Inject

class CoroutinesRepositoryImpl @Inject constructor(
    private val apiService: MockApiService
) : CoroutinesRepository {

    override suspend fun getData(): List<MyData> {
        return secureNetworkRequest { apiService.getData().toMyData() }
    }

    override suspend fun getSequentialNewestAndroidVersions() =
        secureNetworkRequest { apiService.getSequentialNewestAndroidVersions().toMyData() }

    override suspend fun getSequentialFeaturesNewestAndroidVersions() =
        secureNetworkRequest { apiService.getSequentialFeaturesNewestAndroidVersions().toMyData() }

    override suspend fun getError() {
        // get error does not return data, is for retrofit to return an error
        // so is not needed to have variance of secureNetworkRequest(()->Unit) function
        secureNetworkRequest { apiService.getError().toMyData() }
    }


}

private suspend fun secureNetworkRequest(networkRequest: suspend ()-> List<MyData> ): List<MyData>{
    try{
        return networkRequest()
    }catch(e: Exception){
        // throw to next function, probably to catch in ViewModel
        throw e
    }
}
