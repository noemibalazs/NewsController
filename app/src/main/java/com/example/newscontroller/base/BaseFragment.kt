package com.example.newscontroller.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.example.newscontroller.R
import com.example.newscontroller.core.Failure
import org.koin.android.ext.android.get

abstract class BaseFragment : Fragment(){

    open val viewModel: BaseViewModel ?=null

    private val errorMessage: MaterialDialog by lazy {
        return@lazy MaterialDialog(windowContext = context ?: get())
            .cancelable(true)
            .title(R.string.error_title)
            .positiveButton { R.string.error_ok_button }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewAfterOnViewCreated(view)
        setUpObservables(viewLifecycleOwner)
        setUpBaseObservable(viewLifecycleOwner)
    }

    private fun setUpBaseObservable(lifecycleOwner: LifecycleOwner){
        viewModel?.generalObserver?.observe(lifecycleOwner, Observer {
            showError(it)
        })
    }

    private fun  showError(failure: Failure){
        if( !errorMessage.isShowing ){
            errorMessage.show {
                message { getErrorMessage(failure) }
            }
        }
    }

    private fun getErrorMessage(failure: Failure): String{
        return when(failure){
            is Failure.NetworkConnectingError -> {"Network connecting error"}
            is Failure.ResourceNotFoundFailure -> {"Resource not found"}
            is Failure.ResourceParseFailure -> {"Resource parse exception"}
            is Failure.ApiErrorFailure -> {"Api error"}
            is Failure.ServerErrorFailure -> {"Server not found"}
        }
    }


    abstract fun setUpViewAfterOnViewCreated(view: View)
    abstract fun setUpObservables(lifecycleOwner: LifecycleOwner)
}