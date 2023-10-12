package com.chb.flickrphotosoftheday.presentation.viewmodels

import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import com.chb.flickrphotosoftheday.domain.repositories.IPhotosRepository
import com.chb.flickrphotosoftheday.presentation.async.ICoroutineContextProvider
import com.chb.flickrphotosoftheday.presentation.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class PhotoInfoViewModel @Inject constructor(
    contextProvider: ICoroutineContextProvider,
    private val repository: IPhotosRepository
) : BaseViewModel(contextProvider) {

    private val _list = MutableStateFlow<List<PhotoInfo>>(emptyList())
    val list: StateFlow<List<PhotoInfo>> = _list

    fun getPhotoInfoList() {
        launchCoroutineIO {
            repository.getPhotoInfoList().collect { list ->
                _list.emit(list)
            }
        }
    }
}
