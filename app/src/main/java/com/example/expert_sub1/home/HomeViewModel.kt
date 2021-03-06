package com.example.expert_sub1.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.expert_sub1.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(movieUseCase: MovieUseCase) : ViewModel() {
    val movies = movieUseCase.getTrendingMovies().asLiveData()
}

