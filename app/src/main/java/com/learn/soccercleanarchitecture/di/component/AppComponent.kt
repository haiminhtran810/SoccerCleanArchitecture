package com.learn.soccercleanarchitecture.di.component

import com.learn.soccercleanarchitecture.MainApplication
import com.learn.soccercleanarchitecture.di.builder.AppModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, AppModule::class])
interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<MainApplication>
}