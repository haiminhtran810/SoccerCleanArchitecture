package com.learn.soccercleanarchitecture.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentPopularBinding

class PopularFragment : BaseFragment<FragmentPopularBinding, PopularViewModel>() {
    override val viewModel: PopularViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_popular
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            getMoviePopular()
        }

        viewDataBinding.apply {

        }

    }
}