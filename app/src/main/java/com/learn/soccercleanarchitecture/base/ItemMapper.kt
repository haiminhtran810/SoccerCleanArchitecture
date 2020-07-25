package com.learn.soccercleanarchitecture.base

import com.learn.domain.model.Model
import com.learn.soccercleanarchitecture.model.ModelItem

interface ItemMapper<M : Model, MI : ModelItem> {
    fun mapToPresentation(model: M): ModelItem

    fun mapToDomain(modelItem: MI): Model
}