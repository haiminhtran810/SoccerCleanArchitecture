package com.learn.domain.usecase.movie

import com.learn.domain.model.Movie
import com.learn.domain.repository.MovieRepository
import com.learn.domain.usecase.UseCase
import io.reactivex.Single
import javax.inject.Inject

class GetMoviePopularUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) : UseCase<GetMoviePopularUseCase.Params, Single<List<Movie>>>() {

    override fun createObservable(params: Params?): Single<List<Movie>> {
        return when (params) {
            null -> Single.error(Throwable(message = "Params input not null"))
            else -> movieRepository.getMovieListPopular(params.page)
        }
    }

    data class Params(val page: Int)
}