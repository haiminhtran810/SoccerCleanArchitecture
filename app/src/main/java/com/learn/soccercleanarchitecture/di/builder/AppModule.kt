package com.learn.soccercleanarchitecture.di.builder

import android.content.Context
import com.learn.data.di.NetworkModule
import com.learn.data.di.RepositoryModule
import com.learn.soccercleanarchitecture.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, RepositoryModule::class, FragmentBuildersModule::class, NetworkModule::class])
class AppModule {
    @Singleton
    @Provides
    fun providerContext(application: MainApplication): Context {
        return application.applicationContext
    }
}