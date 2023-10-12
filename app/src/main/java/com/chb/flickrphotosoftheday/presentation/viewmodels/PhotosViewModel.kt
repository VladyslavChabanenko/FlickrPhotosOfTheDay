package com.chb.flickrphotosoftheday.presentation.viewmodels

import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.repositories.IPhotosRepository
import com.chb.flickrphotosoftheday.presentation.async.ICoroutineContextProvider
import com.chb.flickrphotosoftheday.presentation.viewmodels.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    contextProvider: ICoroutineContextProvider,
    private val repository: IPhotosRepository
) : BaseViewModel(contextProvider) {

    private val _photoList = MutableSharedFlow<PhotosUI>()
    val photoList: SharedFlow<PhotosUI> = _photoList

    fun getPhotos() {
        launchCoroutineIO {
            _photoList.emit(PhotosUI.Loading)
            repository.getPhotos().collect { photos ->
                _photoList.emit(PhotosUI.Success(photos))
            }
        }
    }

    companion object {
        const val NUM_OF_COLUMNS = 3
    }
}

sealed class PhotosUI {
    object Loading : PhotosUI()
    data class Error(var error: String = "") : PhotosUI()
    data class Success(val data: List<Photo>) : PhotosUI()
}
