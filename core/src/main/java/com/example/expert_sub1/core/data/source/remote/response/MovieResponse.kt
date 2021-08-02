package com.example.expert_sub1.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id") val id: Int,
    @SerializedName(value = "title", alternate = ["name"]) val title: String,
    @SerializedName(value = "release_date", alternate = ["first_air_date"]) val date: String,
    @SerializedName("overview") val description: String,
    @SerializedName("poster_path") val imagePath: String,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("vote_count") val rateCount: Int,
    @SerializedName("media_type") val type: String,
)

