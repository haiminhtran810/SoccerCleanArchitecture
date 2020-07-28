package com.learn.soccercleanarchitecture.base

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.learn.soccercleanarchitecture.BR
import com.learn.soccercleanarchitecture.R
import com.learn.soccercleanarchitecture.extension.showDialog
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

    private var errorMessageDialog: AlertDialog? = null

    var viewDataBinding by autoCleared<T>()

    private var toast: Toast? = null
    private var snackBar: Snackbar? = null
    private var loadingDialog: AlertDialog? = null
    private var messageDialog: AlertDialog? = null

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
        subscriberException()
    }

    protected open fun subscriberException() {
        viewModel.run {
            unexpectedError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.unexpected_error))
            })
            httpUnavailableError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.http_unavailable_error))
            })
            rxMapperError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.rx_mapper_error))
            })
            httpForbiddenError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.http_forbidden_error))
            })
            httpGatewayTimeoutError.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = getString(R.string.no_internet_error))
            })
            errorMessage.observe(viewLifecycleOwner, Observer {
                handleErrorMessage(message = it)
            })
        }
    }

    private fun handleErrorMessage() {

    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    private fun handleErrorMessage(message: String) {
        if (errorMessageDialog?.isShowing != true) {
            errorMessageDialog =
                context?.showDialog(message = message, positiveMessage = getString(R.string.ok))
        }
    }

    override fun onStop() {
        super.onStop()
        errorMessageDialog?.dismiss()
    }
}