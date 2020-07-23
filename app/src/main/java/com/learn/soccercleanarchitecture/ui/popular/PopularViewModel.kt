package com.learn.soccercleanarchitecture.ui.popular

import com.learn.domain.usecase.movie.GetMoviePopularUseCase
import com.learn.soccercleanarchitecture.base.BaseViewModel
import com.learn.soccercleanarchitecture.model.ModelItem
import com.learn.soccercleanarchitecture.model.MovieItemMapper
import com.learn.soccercleanarchitecture.model.PageHeaderItem
import com.learn.soccercleanarchitecture.util.RxUtils
import com.learn.soccercleanarchitecture.util.SingleLiveData
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val movieItemMapper: MovieItemMapper
) : BaseViewModel() {
    val popularData = SingleLiveData<List<ModelItem>>()

    fun getMoviePopular(paging: Int) {
        addDisposable(
            getMoviePopularUseCase.createObservable(GetMoviePopularUseCase.Params(paging))
                .compose(RxUtils.applySingleScheduler()).map {
                    it.map { movie ->
                        movieItemMapper.mapToPresentation(movie)
                    }
                }.subscribe({
                    val newData = ArrayList<ModelItem>()
                    newData.add(PageHeaderItem(1))
                    newData.addAll(it)
                    popularData.value = newData
                }, {
                })
        )
    }
}