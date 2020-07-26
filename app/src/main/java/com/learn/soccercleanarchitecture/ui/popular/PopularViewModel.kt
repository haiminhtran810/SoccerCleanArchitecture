package com.learn.soccercleanarchitecture.ui.popular

import com.learn.domain.usecase.movie.GetMoviePopularUseCase
import com.learn.soccercleanarchitecture.base.BaseLoadMoreRefreshViewModel
import com.learn.soccercleanarchitecture.model.ModelItem
import com.learn.soccercleanarchitecture.model.MovieItemMapper
import com.learn.soccercleanarchitecture.model.PageHeaderItem
import com.learn.soccercleanarchitecture.util.Constants
import com.learn.soccercleanarchitecture.util.RxUtils
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getMoviePopularUseCase: GetMoviePopularUseCase,
    private val movieItemMapper: MovieItemMapper
) : BaseLoadMoreRefreshViewModel<ModelItem>() {

    private fun getMoviePopular(paging: Int) {
        addDisposable(
            getMoviePopularUseCase.createObservable(GetMoviePopularUseCase.Params(paging))
                .compose(RxUtils.applySingleScheduler()).map {
                    it.map { movie ->
                        movieItemMapper.mapToPresentation(movie)
                    }
                }.subscribe({
                    onLoadSuccess(paging, it)
                }, {
                })
        )
    }

    override fun loadData(page: Int) {
        getMoviePopular(page)
    }

    override fun onLoadSuccess(page: Int, items: List<ModelItem>?) {
        // load success then update current page
        currentPage.value = page
        // case load first page then clear data from listItem
        if (currentPage.value == getFirstPage()) {
            listItem.value?.clear()
        }
        // case refresh then reset load more
        if (isRefreshing.value == true) resetLoadMore()
        if (!items.isNullOrEmpty()) {
            val newData = listItem.value ?: ArrayList()
            newData.add(PageHeaderItem(page))
            newData.addAll(items)
            listItem.value = newData
        }
        // check last page
        isLastPage.value = items?.size ?: 0 < getNumberItemPerPage()
        hideLoading()
        isRefreshing.value = false
        isLoadMore.value = false
    }

    override fun getLoadMoreThreshold(): Int {
        return 1
    }

    override fun getNumberItemPerPage(): Int {
        return Constants.DEFAULT_ITEM_PER_PAGE
    }
}