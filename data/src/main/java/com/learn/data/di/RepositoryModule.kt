package com.learn.data.di

import android.content.Context
import com.google.gson.Gson
import com.learn.data.MovieRepositoryImpl
import com.learn.data.local.pref.AppPrefs
import com.learn.data.local.pref.PrefHelper
import com.learn.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePrefHelper(appPrefs: AppPrefs): PrefHelper {
        return appPrefs
    }

    @Provides
    @Singleton
    fun providerAppPrefs(context: Context): AppPrefs {
        return AppPrefs(context, Gson())
    }

    @Provides
    @Singleton
    fun providerMovieRepository(repository: MovieRepositoryImpl): MovieRepository {
        return repository
    }
}