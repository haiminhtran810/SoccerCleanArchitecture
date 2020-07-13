package com.learn.soccercleanarchitecture

import com.learn.soccercleanarchitecture.di.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<MainApplication> =
        DaggerAppComponent.factory().create(this)

}