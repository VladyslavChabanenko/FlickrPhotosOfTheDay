package com.chb.flickrphotosoftheday.data.source

import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo

interface IPhotosRemoteDataSource {
    suspend fun getPhotos(): List<Photo>
    suspend fun getPhotoInfo(id: Long): PhotoInfo
}
