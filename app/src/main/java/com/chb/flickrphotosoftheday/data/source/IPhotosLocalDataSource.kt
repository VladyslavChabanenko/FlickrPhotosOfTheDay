package com.chb.flickrphotosoftheday.data.source

import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo

interface IPhotosLocalDataSource {
    suspend fun getPhotos(): List<Photo>
    suspend fun getPhotoInfo(id: Long): PhotoInfo
    suspend fun getPhotoInfoList(): List<PhotoInfo>
    suspend fun savePhotos(photos: List<Photo>)
    suspend fun savePhotoInfo(info: PhotoInfo)
    suspend fun updateAllPhotos(photosToInsert: List<Photo>, photosToUpdate: List<Photo>, photosToDelete: List<Photo>)
}
