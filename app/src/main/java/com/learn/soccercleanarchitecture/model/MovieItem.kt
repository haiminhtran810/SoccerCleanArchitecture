package com.learn.soccercleanarchitecture.model

import com.learn.domain.model.Movie
import com.learn.soccercleanarchitecture.BuildConfig
import com.learn.soccercleanarchitecture.base.ItemMapper
import javax.inject.Inject

data class MovieItem(
    val id: Int? = 0,
    val adult: Boolean? = false,
    val backdropPath: String? = "",
    val originalLanguage: String? = "",
    val originalTitle: String? = "",
    val overview: String? = "",
    val popularity: Double? = 0.0,
    val posterPath: String? = "",
    val title: String? = "",
    val video: Boolean? = false,
    val voteAverage: Double? = 0.0,
    val voteCount: Int? = 0,
    var isFavorite: Boolean? = false
) : ModelItem() {
    fun getImageLink() = "${BuildConfig.URL_IMAGE}$posterPath"
    fun getImageBackdropPathLink() = "${BuildConfig.URL_IMAGE}$backdropPath"
}

class MovieItemMapper @Inject constructor() : ItemMapper<Movie, MovieItem> {
    override fun mapToPresentation(model: Movie): MovieItem = MovieItem(
        id = model.id,
        adult = model.adult,
        backdropPath = model.backdropPath,
        originalLanguage = model.originalLanguage,
        originalTitle = model.originalTitle,
        overview = model.overview,
        popularity = model.popularity,
        posterPath = model.posterPath,
        title = model.title,
        video = model.video,
        voteAverage = model.voteAverage,
        voteCount = model.voteCount
    )

    override fun mapToDomain(modelItem: MovieItem) = Movie(
        id = modelItem.id,
        adult = modelItem.adult,
        backdropPath = modelItem.backdropPath,
        originalLanguage = modelItem.originalLanguage,
        originalTitle = modelItem.originalTitle,
        overview = modelItem.overview,
        popularity = modelItem.popularity,
        posterPath = modelItem.posterPath,
        title = modelItem.title,
        video = modelItem.video,
        voteAverage = modelItem.voteAverage,
        voteCount = modelItem.voteCount
    )
}