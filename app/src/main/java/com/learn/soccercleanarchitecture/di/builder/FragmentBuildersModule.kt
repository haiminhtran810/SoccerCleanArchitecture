package com.learn.soccercleanarchitecture.di.builder

import com.learn.soccercleanarchitecture.ui.MainActivity
import com.learn.soccercleanarchitecture.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment
}