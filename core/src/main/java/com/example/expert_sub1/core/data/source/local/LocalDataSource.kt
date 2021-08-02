package com.example.expert_sub1.core.data.source.local

import com.example.expert_sub1.core.data.source.local.entity.MovieEntity
import com.example.expert_sub1.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val movieDao: MovieDao) {

    fun getTrendingMovies(): Flow<List<MovieEntity>> = movieDao.getTrendingMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    fun checkFavoriteMovies(id: Int): Flow<List<MovieEntity>> = movieDao.checkFavoriteMovies(id)

    suspend fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavoriteMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFavorite = newState
        movieDao.updateFavoriteMovie(movie)
    }
}