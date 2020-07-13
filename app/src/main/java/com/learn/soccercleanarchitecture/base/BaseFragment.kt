package com.learn.soccercleanarchitecture.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.learn.soccercleanarchitecture.BR
import com.learn.soccercleanarchitecture.util.autoCleared
import dagger.android.support.DaggerFragment
import pub.devrel.easypermissions.EasyPermissions
import javax.inject.Inject

abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel> : DaggerFragment(),
    EasyPermissions.PermissionCallbacks {

    abstract val viewModel: V

    @get:LayoutRes
    abstract val layoutId: Int

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var viewDataBinding by autoCleared<T>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding.apply {
            setVariable(BR.viewModel, viewModel)
            executePendingBindings()
            lifecycleOwner = this@BaseFragment
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }
}