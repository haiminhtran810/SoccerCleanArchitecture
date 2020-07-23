package com.learn.soccercleanarchitecture.ui.popular

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentPopularBinding
import com.learn.soccercleanarchitecture.ui.popular.adapter.PopularAdapter

class PopularFragment : BaseFragment<FragmentPopularBinding, PopularViewModel>() {
    private lateinit var movieAdapter: PopularAdapter
    override val viewModel: PopularViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_popular
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = PopularAdapter()
        viewModel.apply {
            getMoviePopular(1)
            popularData.observe(viewLifecycleOwner, Observer {
                movieAdapter.submitList(it)
            })
        }

        viewDataBinding.apply {
            recyclerViewMovies.adapter = movieAdapter
        }
    }
}