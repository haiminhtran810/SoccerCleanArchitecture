package com.learn.soccercleanarchitecture.base

import androidx.lifecycle.MutableLiveData
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.learn.soccercleanarchitecture.util.Constants
import com.learn.soccercleanarchitecture.widgets.EndlessRecyclerOnScrollListener

abstract class BaseLoadMoreRefreshViewModel<Item>() : BaseViewModel() {

    // refresh flag
    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    // refresh listener for swipe refresh layout
    val onRefreshListener = SwipeRefreshLayout.OnRefreshListener {
        doRefresh()
    }

    private fun doRefresh() {
        if (isLoading.value == true
            || isRefreshing.value == true
        ) return
        isRefreshing.value = true
        refreshData()
    }

    // load more flag
    val isLoadMore = MutableLiveData<Boolean>().apply { value = false }

    // current page
    protected val currentPage = MutableLiveData<Int>().apply { value = getPreFirstPage() }

    // last page flag
    val isLastPage = MutableLiveData<Boolean>().apply { value = false }

    // scroll listener for recycler view
    val onScrollListener = object : EndlessRecyclerOnScrollListener(getLoadMoreThreshold()) {
        override fun onLoadMore() {
            doLoadMore()
        }
    }

    fun doLoadMore() {
        if (isLoading.value == true
            || isRefreshing.value == true
            || isLoadMore.value == true
            || isLastPage.value == true
        ) return
        isLoadMore.value = true
        loadMore()
    }

    // item list
    val listItem = MutableLiveData<ArrayList<Item>>()

    // empty list flag
    val isEmptyList = MutableLiveData<Boolean>().apply { value = false }

    /**
     * load data
     */
    abstract fun loadData(page: Int)

    /**
     * check first time load data
     */
    private fun isFirst() = currentPage.value == getPreFirstPage()
            && listItem.value?.isEmpty() ?: true

    /**
     * first load
     */
    fun firstLoad() {
        if (isFirst()) {
            showLoading()
            loadData(getFirstPage())
        }
    }

    /**
     * load first page
     */
    fun refreshData() {
        loadData(getFirstPage())
    }

    /**
     * load next page
     */
    fun loadMore() {
        loadData(currentPage.value?.plus(1) ?: getFirstPage())
    }

    /**
     * override if first page is not 1
     */
    open fun getFirstPage() = Constants.DEFAULT_FIRST_PAGE

    private fun getPreFirstPage() = getFirstPage() - 1

    /**
     * override if need change number visible threshold
     */
    open fun getLoadMoreThreshold() = Constants.DEFAULT_NUM_VISIBLE_THRESHOLD

    /**
     * override if need change number item per page
     */
    open fun getNumberItemPerPage() = Constants.DEFAULT_ITEM_PER_PAGE

    /**
     * reset load more
     */
    fun resetLoadMore() {
        onScrollListener.resetOnLoadMore()
        isLastPage.value = false
    }

    /**
     * handle load success
     */
    protected open fun onLoadSuccess(page: Int, items: List<Item>?) {
        // load success then update current page
        currentPage.value = page
        // case load first page then clear data from listItem
        if (currentPage.value == getFirstPage()) listItem.value?.clear()
        // case refresh then reset load more
        if (isRefreshing.value == true) resetLoadMore()

        // add new data to listItem
        val newList = listItem.value ?: ArrayList()
        newList.addAll(items ?: listOf())
        listItem.value = newList

        // check last page
        isLastPage.value = items?.size ?: 0 < getNumberItemPerPage()

        // reset load
        hideLoading()
        isRefreshing.value = false
        isLoadMore.value = false

        // check empty list
        checkEmptyList()
    }

    /**
     * handle load fail
     */
    override fun onError(throwable: Throwable) {
        super.onError(throwable)
        // reset load
        isRefreshing.value = false
        isLoadMore.value = false

        // check empty list
        checkEmptyList()
    }

    /**
     * check list is empty
     */
    protected fun checkEmptyList() {
        isEmptyList.value = listItem.value?.isEmpty() ?: true
    }

}