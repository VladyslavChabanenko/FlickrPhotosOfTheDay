package com.chb.flickrphotosoftheday.presentation.viewmodels.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chb.flickrphotosoftheday.presentation.async.ICoroutineContextProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class BaseViewModel(private val contextProvider: ICoroutineContextProvider) : ViewModel() {

    private val job: Job = Job()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception)
    }

    protected fun launchCoroutineIO(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.io + job + coroutineExceptionHandler) {
            block()
        }
    }

    protected fun launchCoroutineMain(block: suspend CoroutineScope.() -> Unit): Job {
        return viewModelScope.launch(contextProvider.main + job + coroutineExceptionHandler) {
            block()
        }
    }

    public override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}
