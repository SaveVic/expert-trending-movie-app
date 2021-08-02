package com.example.expert_sub1.core.utils

import com.example.expert_sub1.core.data.source.local.entity.MovieEntity
import com.example.expert_sub1.core.data.source.remote.response.MovieResponse
import com.example.expert_sub1.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        return input.map {
            MovieEntity(
                id = it.id,
                title = it.title,
                date = it.date,
                description = it.description,
                imagePath = it.imagePath,
                rating = it.rating,
                rateCount = it.rateCount,
                type = it.type,
                isFavorite = false
            )
        }
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                date = it.date,
                description = it.description,
                imagePath = it.imagePath,
                rating = it.rating,
                rateCount = it.rateCount,
                type = it.type,
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        date = input.date,
        description = input.description,
        imagePath = input.imagePath,
        rating = input.rating,
        rateCount = input.rateCount,
        type = input.type,
        isFavorite = input.isFavorite
    )
}