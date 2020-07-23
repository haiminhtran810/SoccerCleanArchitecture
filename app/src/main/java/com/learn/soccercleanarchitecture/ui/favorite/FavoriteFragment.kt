package com.learn.soccercleanarchitecture.ui.favorite

import androidx.fragment.app.viewModels
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    override val viewModel: FavoriteViewModel by viewModels { viewModelFactory }
    override val layoutId = R.layout.fragment_favorite
}