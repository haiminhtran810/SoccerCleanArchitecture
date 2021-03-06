package com.learn.data.local.dao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learn.data.local.dao.MovieDao
import com.learn.data.model.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}