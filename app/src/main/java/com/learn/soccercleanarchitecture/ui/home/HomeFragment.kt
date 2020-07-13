package com.learn.soccercleanarchitecture.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMoviePopular()
    }
}