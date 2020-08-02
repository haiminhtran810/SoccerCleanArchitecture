package com.learn.data.local.dao

import androidx.room.*
import com.learn.data.model.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    fun getMovies(): Single<List<MovieEntity>>

    @Insert
    fun insertAll(vararg movies: MovieEntity): Completable

    @Update
    fun updateMovie(movie: MovieEntity)

    @Delete
    fun delete(movie: MovieEntity)
}