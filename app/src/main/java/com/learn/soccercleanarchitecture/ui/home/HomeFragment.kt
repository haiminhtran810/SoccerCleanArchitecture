package com.learn.soccercleanarchitecture.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpNavigation()
    }

    private fun setUpNavigation() {
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_container)
        bottom_nav.setupWithNavController(navController)
    }

}