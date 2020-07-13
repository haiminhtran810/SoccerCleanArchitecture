package com.learn.data.remote.api

import com.learn.data.remote.response.GetMoviesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getMovieListPopular(@Query(ApiParams.PAGE) page: Int): Single<GetMoviesResponse>
}

object ApiParams {
    const val PAGE = "page"
    const val MOVIE_ID = "movie_id"
}