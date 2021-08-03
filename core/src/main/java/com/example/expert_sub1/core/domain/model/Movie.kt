package com.example.expert_sub1.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val title: String,
    val date: String,
    val description: String,
    val imagePath: String,
    val rating: Double,
    val rateCount: Int,
    val type: String,
    val isFavorite: Boolean
) : Parcelable