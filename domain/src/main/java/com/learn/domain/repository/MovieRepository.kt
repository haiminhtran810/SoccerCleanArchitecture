package com.learn.domain.repository

import com.learn.domain.model.Movie
import io.reactivex.Completable
import io.reactivex.Single

interface MovieRepository {
    fun getMovieListPopular(page: Int): Single<List<Movie>>

    // Database
    fun updateMovieLocal(movie: Movie): Completable
    fun insertMovieLocal(movie: Movie): Completable
    fun getMoviesLocal(): Single<List<Movie>>
}