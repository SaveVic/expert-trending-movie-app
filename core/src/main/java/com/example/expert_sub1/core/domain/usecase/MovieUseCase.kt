package com.example.expert_sub1.core.domain.usecase

import com.example.expert_sub1.core.data.Resource
import com.example.expert_sub1.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getTrendingMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun checkFavoriteMovies(id: Int): Flow<Boolean>
    fun setFavoriteMovie(movie: Movie, state: Boolean)
}