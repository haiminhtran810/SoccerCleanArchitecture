package com.learn.data.base

import com.learn.domain.model.Model

interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}