package com.example.expert_sub1.core.data.source.remote.network

import com.example.expert_sub1.core.BuildConfig
import com.example.expert_sub1.core.data.source.remote.response.ListMovieResponse
import com.example.expert_sub1.core.data.source.remote.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("trending/all/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ListMovieResponse
}
