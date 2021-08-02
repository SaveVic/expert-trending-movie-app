package com.example.expert_sub1.core.di

import com.example.expert_sub1.core.data.MovieRepository
import com.example.expert_sub1.core.di.DatabaseModule
import com.example.expert_sub1.core.di.NetworkModule
import com.example.expert_sub1.core.domain.repository.IMovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(movieRepository: MovieRepository): IMovieRepository

}