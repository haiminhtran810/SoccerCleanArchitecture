package com.learn.soccercleanarchitecture.di.builder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.learn.soccercleanarchitecture.ViewModelProviderFactory
import com.learn.soccercleanarchitecture.di.annotation.ViewModelKey
import com.learn.soccercleanarchitecture.ui.MainViewModel
import com.learn.soccercleanarchitecture.ui.favorite.FavoriteViewModel
import com.learn.soccercleanarchitecture.ui.home.HomeViewModel
import com.learn.soccercleanarchitecture.ui.popular.PopularViewModel
import com.learn.soccercleanarchitecture.ui.top.TopViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(providerFactory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TopViewModel::class)
    abstract fun bindTopViewModel(topViewModel: TopViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(favoriteViewModel: FavoriteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    abstract fun bindPopularViewModel(popularViewModel: PopularViewModel): ViewModel
}