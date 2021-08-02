package com.example.expert_sub1.core.data

import com.example.expert_sub1.core.data.source.local.LocalDataSource
import com.example.expert_sub1.core.data.source.remote.RemoteDataSource
import com.example.expert_sub1.core.data.source.remote.network.ApiResponse
import com.example.expert_sub1.core.data.source.remote.response.MovieResponse
import com.example.expert_sub1.core.domain.model.Movie
import com.example.expert_sub1.core.domain.repository.IMovieRepository
import com.example.expert_sub1.core.utils.AppExecutors
import com.example.expert_sub1.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IMovieRepository {

    override fun getTrendingMovies(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getTrendingMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
//                data == null || data.isEmpty()
                true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getTrendingMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun checkFavoriteMovie(id: Int): Flow<Boolean> {
        return localDataSource.checkFavoriteMovies(id).map {
            it.isNotEmpty() && it.fold(false, { value, data -> value || data.isFavorite })
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean) {
        val tourismEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(tourismEntity, state) }
    }
}

