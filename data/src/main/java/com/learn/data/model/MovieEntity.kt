package com.learn.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.learn.data.base.EntityMapper
import com.learn.data.base.ModelEntity
import com.learn.domain.model.Movie
import javax.inject.Inject

@Entity
data class MovieEntity(
    @PrimaryKey
    @SerializedName("id")
    val id: Int? = 0,
    @ColumnInfo(name = "adult")
    @SerializedName("adult")
    val adult: Boolean? = false,
    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String? = "",
    @ColumnInfo(name = "original_language")
    @SerializedName("original_language")
    val originalLanguage: String? = "",
    @ColumnInfo(name = "original_title")
    @SerializedName("original_title")
    val originalTitle: String? = "",
    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String? = "",
    @ColumnInfo(name = "popularity")
    @SerializedName("popularity")
    val popularity: Double? = 0.0,
    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    val posterPath: String? = "",
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String? = "",
    @ColumnInfo(name = "video")
    @SerializedName("video")
    val video: Boolean? = false,
    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double? = 0.0,
    @ColumnInfo(name = "vote_count")
    @SerializedName("vote_count")
    val voteCount: Int? = 0
) : ModelEntity()

class MovieEntityMapper @Inject constructor() : EntityMapper<Movie, MovieEntity> {
    override fun mapToDomain(entity: MovieEntity) = Movie(
        id = entity.id,
        adult = entity.adult,
        backdropPath = entity.backdropPath,
        originalLanguage = entity.originalLanguage,
        originalTitle = entity.originalTitle,
        overview = entity.overview,
        popularity = entity.popularity,
        posterPath = entity.posterPath,
        title = entity.title,
        video = entity.video,
        voteAverage = entity.voteAverage,
        voteCount = entity.voteCount
    )

    override fun mapToEntity(model: Movie) = MovieEntity(
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
}