package com.linkinaplications.llinkinktor.data.remote

import com.linkinaplications.llinkinktor.data.remote.dto.Movie
import com.linkinaplications.llinkinktor.data.remote.dto.MovieList
import com.linkinaplications.llinkinktor.data.remote.dto.PostRequest
import com.linkinaplications.llinkinktor.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logging

interface PostsService {
    suspend fun getPosts(apiKey: String): MovieList

    suspend fun createPosts(postRequest: PostRequest): PostResponse?

    companion object {
        fun create(): PostsService {
            return PostsServiceImpl(
                client = HttpClient(Android) {
                    install(Logging) {
                        level = LogLevel.ALL
                    }
                    install(JsonFeature) {
                        serializer = KotlinxSerializer(json = kotlinx.serialization.json.Json {
                            isLenient = true
                            ignoreUnknownKeys = true
                        })
                    }
                }
            )
        }
    }
}