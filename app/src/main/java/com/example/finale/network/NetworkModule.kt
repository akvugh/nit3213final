package com.example.finale.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideApiRetrieve(): ApiRetrieve {
        return RetrofitClient().apiService
    }
}