package com.learn.soccercleanarchitecture.di.builder

import com.learn.soccercleanarchitecture.ui.MainActivity
import com.learn.soccercleanarchitecture.ui.favorite.FavoriteFragment
import com.learn.soccercleanarchitecture.ui.home.HomeFragment
import com.learn.soccercleanarchitecture.ui.popular.PopularFragment
import com.learn.soccercleanarchitecture.ui.top.TopFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeTopFragment(): TopFragment

    @ContributesAndroidInjector
    abstract fun contributePopularFragment(): PopularFragment

    @ContributesAndroidInjector
    abstract fun contributeFavoriteFragment(): FavoriteFragment
}