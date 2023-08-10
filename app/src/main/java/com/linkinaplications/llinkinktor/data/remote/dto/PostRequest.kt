package com.linkinaplications.llinkinktor.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostRequest(
    val body : String,
    val tittle : String,
    val userId: Int
)