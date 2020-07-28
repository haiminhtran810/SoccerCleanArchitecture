package com.learn.data

import com.learn.data.model.MovieEntity
import com.learn.domain.model.Movie

fun createMovieEntity() = MovieEntity(
    id = 0,
    adult = true,
    backdropPath = "https:image.png",
    originalLanguage = "En",
    originalTitle = "This is movie",
    overview = "this is overview",
    popularity = 4.5,
    posterPath = "this posterPath",
    title = "this is title",
    video = false,
    voteAverage = 6.6,
    voteCount = 8
)

fun createMovie() = Movie(
    id = 0,
    adult = true,
    backdropPath = "https:image.png",
    originalLanguage = "En",
    originalTitle = "This is movie",
    overview = "this is overview",
    popularity = 4.5,
    posterPath = "this posterPath",
    title = "this is title",
    video = false,
    voteAverage = 6.6,
    voteCount = 8
)