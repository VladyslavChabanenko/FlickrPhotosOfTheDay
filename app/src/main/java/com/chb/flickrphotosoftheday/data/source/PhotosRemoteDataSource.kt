package com.chb.flickrphotosoftheday.data.source

import com.chb.flickrphotosoftheday.data.remote.api.PhotosApiService
import com.chb.flickrphotosoftheday.data.remote.mappers.PhotoInfoRemoteMapper
import com.chb.flickrphotosoftheday.data.remote.mappers.PhotosRemoteMapper
import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import javax.inject.Inject

class PhotosRemoteDataSource @Inject constructor(
    private val photosApiService: PhotosApiService,
    private val photoMapper: PhotosRemoteMapper,
    private val photoInfoMapper: PhotoInfoRemoteMapper
) : IPhotosRemoteDataSource {
    override suspend fun getPhotos(): List<Photo> {
        return photosApiService.getRecentPhotos().photosModel.photos.map {
            photoMapper.mapFromModel(it)
        }
    }

    override suspend fun getPhotoInfo(id: Long): PhotoInfo {
        val response = photosApiService.getPhotoInfo(id = id)
        return photoInfoMapper.mapFromModel(response.photoModel)
    }
}
