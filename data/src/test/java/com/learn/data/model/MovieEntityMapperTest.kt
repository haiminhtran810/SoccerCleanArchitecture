package com.learn.data.model

import com.learn.data.createMovie
import com.learn.data.createMovieEntity
import org.junit.Before
import org.junit.Test

class MovieEntityMapperTest {
    private lateinit var movieEntityMapper: MovieEntityMapper

    @Before
    fun setUp() {
        movieEntityMapper = MovieEntityMapper()
    }

    @Test
    fun mapEntityToDomainTest() {
        val entity = createMovieEntity()
        val model = movieEntityMapper.mapToDomain(entity)
        assert(entity?.id == model?.id)
        assert(entity?.backdropPath == model?.backdropPath)
        assert(entity?.adult == model?.adult)
        assert(entity?.video == model?.video)
        assert(entity?.overview == model?.overview)
        assert(entity?.popularity == model?.popularity)
        assert(entity?.posterPath == model?.posterPath)
    }

    @Test
    fun mapDomainToEntityTest() {
        val model = createMovie()
        val entity = movieEntityMapper.mapToEntity(model)
        assert(entity?.id == model?.id)
        assert(entity?.backdropPath == model?.backdropPath)
        assert(entity?.adult == model?.adult)
        assert(entity?.video == model?.video)
        assert(entity?.overview == model?.overview)
        assert(entity?.popularity == model?.popularity)
        assert(entity?.posterPath == model?.posterPath)
    }
}