package com.example.expert_sub1.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.expert_sub1.core.domain.model.Movie
import com.example.expert_sub1.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun checkFavorite(movie: Movie) = movieUseCase.checkFavoriteMovies(movie.id).asLiveData()

    fun setFavoriteMovie(movie: Movie, newStatus: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newStatus)
}

