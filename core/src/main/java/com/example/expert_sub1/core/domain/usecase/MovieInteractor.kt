package com.example.expert_sub1.core.domain.usecase

import com.example.expert_sub1.core.domain.model.Movie
import com.example.expert_sub1.core.domain.repository.IMovieRepository
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: IMovieRepository):
    MovieUseCase {

    override fun getTrendingMovies() = movieRepository.getTrendingMovies()

    override fun getFavoriteMovies() = movieRepository.getFavoriteMovies()

    override fun checkFavoriteMovies(id: Int) = movieRepository.checkFavoriteMovie(id)

    override fun setFavoriteMovie(movie: Movie, state: Boolean) = movieRepository.setFavoriteMovie(movie, state)
}