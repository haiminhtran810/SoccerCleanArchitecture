package com.learn.data

import com.learn.data.model.MovieEntityMapper
import com.learn.data.remote.api.MovieApi
import com.learn.domain.model.Movie
import com.learn.domain.repository.MovieRepository
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieEntityMapper: MovieEntityMapper
) : MovieRepository {
    override fun getMovieListPopular(page: Int): Single<List<Movie>> {
        return movieApi.getMovieListPopular(page).map {
            it.results.map { movie ->
                movieEntityMapper.mapToDomain(movie)
            }
        }
    }
}
