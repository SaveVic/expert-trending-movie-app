package com.example.expert_sub1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("results") val movies: List<MovieResponse>
)