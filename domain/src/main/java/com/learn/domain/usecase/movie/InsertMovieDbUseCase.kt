package com.learn.domain.usecase.movie

import com.learn.domain.model.Movie
import com.learn.domain.repository.MovieRepository
import com.learn.domain.usecase.UseCase
import io.reactivex.Completable
import javax.inject.Inject

class InsertMovieDbUseCase @Inject constructor(private val movieRepository: MovieRepository) :
    UseCase<InsertMovieDbUseCase.Params, Completable>() {

    override fun createObservable(params: Params?): Completable {
        params?.let {
            return movieRepository.insertMovieLocal(params?.movie)
        }
        throw Throwable("This param can not be empty")
    }

    data class Params(val movie: Movie)
}