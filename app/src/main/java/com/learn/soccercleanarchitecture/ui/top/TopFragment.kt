package com.learn.soccercleanarchitecture.ui.top

import androidx.fragment.app.viewModels
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.base.BaseFragment
import com.learn.soccercleanarchitecture.databinding.FragmentTopBinding

class TopFragment : BaseFragment<FragmentTopBinding, TopViewModel>() {
    override val viewModel: TopViewModel by viewModels { viewModelFactory }
    override val layoutId = R.layout.fragment_top
}