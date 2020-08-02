package com.learn.soccercleanarchitecture.ui.popular

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentPopularBinding
import com.learn.soccercleanarchitecture.model.MovieItem
import com.learn.soccercleanarchitecture.ui.popular.adapter.PopularAdapter

class PopularFragment : BaseFragment<FragmentPopularBinding, PopularViewModel>() {
    private lateinit var movieAdapter: PopularAdapter
    override val viewModel: PopularViewModel by viewModels { viewModelFactory }
    override val layoutId: Int = R.layout.fragment_popular
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = PopularAdapter({
            onClickMovieDetail(it)
        }, {
            onClickMovieFavorite(it)
        })
        viewModel.apply {
            listItem.observe(viewLifecycleOwner, Observer {
                movieAdapter.submitList(it)
            })
            if (listItem.value.isNullOrEmpty()) {
                firstLoad()
            }
            movieInsertSuccess.observe(viewLifecycleOwner, Observer {
                Toast.makeText(requireContext(), "Movie insert success", Toast.LENGTH_LONG).show()
            })
        }

        viewDataBinding.apply {
            recyclerViewMovies.adapter = movieAdapter
            swipeRefreshLayout.setOnRefreshListener {
                viewModel?.apply {
                    resetLoadMore()
                    refreshData()
                }
            }
        }
    }

    private fun onClickMovieDetail(movieItem: MovieItem) {
        //TODO: The method will be done the after pull
    }

    private fun onClickMovieFavorite(movieItem: MovieItem) {
        viewModel.insertMovieToDB(movieItem)
    }
}