package com.linkinaplications.llinkinktor.data.remote

import com.linkinaplications.llinkinktor.data.remote.dto.Movie
import com.linkinaplications.llinkinktor.data.remote.dto.MovieList
import com.linkinaplications.llinkinktor.data.remote.dto.PostRequest
import com.linkinaplications.llinkinktor.data.remote.dto.PostResponse
import io.ktor.client.HttpClient
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.parametersOf
import java.lang.Exception

class PostsServiceImpl(
    private val client : HttpClient
) : PostsService {
    override suspend fun getPosts(apiKey : String): MovieList {
        return try {
            client.get {
                url(HttpRoutes.POPULAR)
                parameter("api_key", apiKey)


            }
        }catch (e: RedirectResponseException){
            //3xx - responses
            println("Error ${e.response.status.description}")
            MovieList()
        }catch (e: ClientRequestException){
            //4xx - responses
            println("Error ${e.response.status.description}")
            MovieList()
        }catch (e: ServerResponseException){
            //5xx - responses
            println("Error ${e.response.status.description}")
            MovieList()
        }catch (e: Exception){
            //4xx - responses
            println("Error ${e.message}")
            MovieList()
        }
    }

    override suspend fun createPosts(postRequest: PostRequest): PostResponse? {
        return try {
            client.post<PostResponse> {
                url(HttpRoutes.POPULAR)
                contentType(ContentType.Application.Json)
                body = postRequest

            }
        }catch (e: RedirectResponseException){
            //3xx - responses
            println("Error ${e.response.status.description}")
            null
        }catch (e: ClientRequestException){
            //4xx - responses
            println("Error ${e.response.status.description}")
            null
        }catch (e: ServerResponseException){
            //5xx - responses
            println("Error ${e.response.status.description}")
            null
        }catch (e: Exception){
            //4xx - responses
            println("Error ${e.message}")
            null
        }
    }
}