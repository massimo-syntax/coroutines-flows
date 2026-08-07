package com.example.data.di

import com.example.data.remote.CloudServer
import com.example.data.remote.MockApiService
import com.example.data.remote.MockInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideMockApiService(
        // Potential dependencies of this type
    ): MockApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(MockInterceptor(CloudServer))
            .build()
        return Retrofit.Builder()
            .baseUrl("https://url.api/") // dummy URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MockApiService::class.java)
    }
}

