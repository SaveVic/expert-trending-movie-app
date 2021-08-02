package com.example.expert_sub1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.expert_sub1.core.domain.usecase.MovieUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val favoriteMovie = movieUseCase.getFavoriteMovies().asLiveData()
}

