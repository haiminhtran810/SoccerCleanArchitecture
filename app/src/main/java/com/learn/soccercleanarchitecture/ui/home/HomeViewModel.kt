package com.learn.soccercleanarchitecture.ui.home

import com.learn.domain.usecase.movie.GetMoviePopularUseCase
import com.learn.soccercleanarchitecture.base.BaseViewModel
import com.learn.soccercleanarchitecture.model.MovieItem
import com.learn.soccercleanarchitecture.model.MovieItemMapper
import com.learn.soccercleanarchitecture.util.RxUtils
import com.learn.soccercleanarchitecture.util.SingleLiveData
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val movieItemMapper: MovieItemMapper
) :
    BaseViewModel() {
    private val popularData = SingleLiveData<List<MovieItem>>()

    fun getMoviePopular() {
        addDisposable(
            getMoviePopularUseCase.createObservable(GetMoviePopularUseCase.Params(1))
                .compose(RxUtils.applySingleScheduler()).map {
                    it.map { movie ->
                        movieItemMapper.mapToPresentation(movie)
                    }
                }.subscribe({
                    popularData.value = it
                }, {
                    val a = it
                })
        )
    }
}