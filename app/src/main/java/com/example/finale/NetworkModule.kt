package com.example.finale

import com.example.finale.network.ApiRetrieve
import com.example.finale.network.RetrofitClient
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