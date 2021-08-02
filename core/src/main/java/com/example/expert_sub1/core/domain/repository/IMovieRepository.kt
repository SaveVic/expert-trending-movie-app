package com.example.expert_sub1.core.domain.repository

import com.example.expert_sub1.core.data.Resource
import com.example.expert_sub1.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getTrendingMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun checkFavoriteMovie(id: Int): Flow<Boolean>

    fun setFavoriteMovie(movie: Movie, state: Boolean)

}