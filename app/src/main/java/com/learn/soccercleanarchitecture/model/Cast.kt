package com.learn.soccercleanarchitecture.model

import com.learn.soccercleanarchitecture.base.ModelItem

data class Cast(
    val castId: Int? = 0,
    val character: String? = "",
    val creditId: String? = "",
    val gender: Int? = 0,
    val id: Int? = 0,
    val name: String? = "",
    val order: Int? = 0,
    val profilePath: String? = ""
) : ModelItem()