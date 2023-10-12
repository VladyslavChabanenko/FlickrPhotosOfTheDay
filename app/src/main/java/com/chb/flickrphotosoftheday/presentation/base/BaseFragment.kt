package com.chb.flickrphotosoftheday.presentation.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.chb.flickrphotosoftheday.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import timber.log.Timber

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    protected lateinit var binding: VB

    abstract fun getViewBinding(): VB

    private var loadingDialog: Dialog? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = getViewBinding()
        return binding.root
    }

    protected open fun showProgressBar(show: Boolean) {
        if (show) showLoadingDialog() else dismissLoadingDialog()
    }

    private fun showLoadingDialog(
        cancelable: Boolean = false,
        canceledOnTouchOutside: Boolean = false
    ): AlertDialog? {
        return MaterialAlertDialogBuilder(context ?: return null).apply {
            setView(R.layout.layout_loading_dialog)
        }.create().let { dialog ->
            dialog.setCancelable(cancelable)
            dialog.setCanceledOnTouchOutside(canceledOnTouchOutside)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            if (loadingDialog?.isShowing == true) {
                loadingDialog?.dismiss()
            }
            loadingDialog = dialog
            dialog.show()
            dialog
        }
    }

    private fun dismissLoadingDialog() {
        if (loadingDialog?.isShowing == true) {
            loadingDialog?.dismiss()
        }
    }

    protected open fun handleErrorMessage(message: String?) {
        if (message.isNullOrBlank()) return
        dismissLoadingDialog()
        Timber.e(message)
    }
}
