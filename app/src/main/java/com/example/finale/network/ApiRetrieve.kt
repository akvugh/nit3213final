package com.example.finale.network

import com.example.finale.data.LoginRequest
import com.example.finale.data.KeypassResponse
import com.example.finale.data.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiRetrieve {

    @POST("/footscray/auth")
    suspend fun getkeypass(@Body data: LoginRequest): LoginResponse

    @GET("/dashboard/fashion")
    suspend fun getAllEntities(): KeypassResponse

    @GET("entities/{id}")
    suspend fun getObjectById(@Path("id") id: Int): KeypassResponse
}