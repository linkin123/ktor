package com.linkinaplications.llinkinktor.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostResponse(
    val body : String,
    val title : String,
    val id: Int,
    val userId: Int
)
@Serializable
data class MovieList(
    val results: List<Movie> = listOf()
)
@Serializable
data class Movie(
    val id: Int = -1,
    val adult: Boolean = false,
    val backdrop_path: String? = "",
    val original_title: String = "",
    val original_language: String = "",
    val overview: String = "",
    val popularity: Double = -1.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Float = 0F,
    val vote_count: Int = -1,
    val movie_type : String = ""
)

