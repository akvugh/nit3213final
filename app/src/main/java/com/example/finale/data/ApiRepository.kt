package com.example.finale.data

import com.example.finale.network.ApiRetrieve
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiService: ApiRetrieve // Now ApiRetrieve is injected directly
) {

    suspend fun getkeypass(newObject: LoginRequest): LoginResponse {
        return apiService.getkeypass(newObject)
    }

    suspend fun getAllEntities(): KeypassResponse {
        return apiService.getAllEntities()
    }

    suspend fun getObjectById(id: Int): KeypassResponse {
        return apiService.getObjectById(id)
    }
}