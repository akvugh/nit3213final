package com.example.finale.data

// Response from GET request sent to the API
data class KeypassResponse(
    val entities: List<Entity>,
    val entityTotal: Int
)