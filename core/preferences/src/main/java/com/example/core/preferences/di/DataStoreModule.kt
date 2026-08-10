package com.example.core.preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton

private const val THEME_DATASTORE_NAME = "theme_preferences"

private val Context.themeDataStore: DataStore<Preferences>
        by preferencesDataStore(name = THEME_DATASTORE_NAME)

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideThemeDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.themeDataStore
}