package com.example.data.di

import com.example.data.repository.CoroutinesRepositoryImpl
import com.example.data.repository.MyRepositoryImpl
import com.example.domain.repository.CoroutinesRepository
import com.example.domain.repository.MyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMyRepository(
        myRepositoryImpl: MyRepositoryImpl
    ): MyRepository
}


@Module
@InstallIn(SingletonComponent::class)
abstract class coroutinesRepositoryModule {
    @Binds
    abstract fun bindMyRepository(
        coroutinesRepository: CoroutinesRepositoryImpl
    ): CoroutinesRepository
}