package com.chb.flickrphotosoftheday.domain.repositories

import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import kotlinx.coroutines.flow.Flow

interface IPhotosRepository {
    suspend fun getPhotos(): Flow<List<Photo>>
    suspend fun getPhotoInfo(id: Long): Flow<PhotoInfo>
    suspend fun getPhotoInfoList(): Flow<List<PhotoInfo>>
    suspend fun updatePhotos()
}
