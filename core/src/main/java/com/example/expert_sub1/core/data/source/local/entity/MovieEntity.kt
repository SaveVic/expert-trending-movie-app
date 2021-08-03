package com.example.expert_sub1.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "imagePath") val imagePath: String,
    @ColumnInfo(name = "rating") val rating: Double,
    @ColumnInfo(name = "rateCount") val rateCount: Int,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "isFavorite") var isFavorite: Boolean = false
)
