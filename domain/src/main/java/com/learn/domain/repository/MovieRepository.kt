package com.learn.domain.repository

import com.learn.domain.model.Movie
import io.reactivex.Single

interface MovieRepository {
    fun getMovieListPopular(page: Int): Single<List<Movie>>
}