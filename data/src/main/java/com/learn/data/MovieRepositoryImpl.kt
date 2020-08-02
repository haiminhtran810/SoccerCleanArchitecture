package com.learn.data

import com.learn.data.local.dao.MovieDao
import com.learn.data.model.MovieEntityMapper
import com.learn.data.remote.api.MovieApi
import com.learn.domain.model.Movie
import com.learn.domain.repository.MovieRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi,
    private val movieDao: MovieDao,
    private val movieEntityMapper: MovieEntityMapper
) : MovieRepository {
    override fun getMovieListPopular(page: Int): Single<List<Movie>> {
        return movieApi.getMovieListPopular(page).map {
            it.results.map { movie ->
                movieEntityMapper.mapToDomain(movie)
            }
        }
    }

    override fun updateMovieLocal(movie: Movie): Completable {
        return Completable.create {
            movieDao.updateMovie(movieEntityMapper.mapToEntity(movie))
        }
    }

    override fun insertMovieLocal(movie: Movie): Completable {
        return Completable.fromAction {
            movieDao.insertAll(movieEntityMapper.mapToEntity(movie))
        }
    }

    override fun getMoviesLocal(): Single<List<Movie>> {
        return movieDao.getMovies().map {
            it.map { movieEntity ->
                movieEntityMapper.mapToDomain(movieEntity)
            }
        }
    }
}
