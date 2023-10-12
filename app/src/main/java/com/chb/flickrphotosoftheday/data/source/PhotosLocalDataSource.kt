package com.chb.flickrphotosoftheday.data.source

import com.chb.flickrphotosoftheday.data.local.dao.PhotosDao
import com.chb.flickrphotosoftheday.data.local.mappers.PhotoInfoLocalMapper
import com.chb.flickrphotosoftheday.data.local.mappers.PhotoLocalMapper
import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import javax.inject.Inject

class PhotosLocalDataSource @Inject constructor(
    private val dao: PhotosDao,
    private val photoMapper: PhotoLocalMapper,
    private val photoInfoMapper: PhotoInfoLocalMapper
) : IPhotosLocalDataSource {

    override suspend fun getPhotos(): List<Photo> {
        return dao.getPhotos().map {
            photoMapper.mapFromCached(it)
        }
    }

    override suspend fun getPhotoInfoList(): List<PhotoInfo> {
        return dao.getPhotoInfoList().map {
            val photo = dao.getPhoto(it.id)
            val info = photoInfoMapper.mapFromCached(it)
                .apply {
                    url = photo.url
                    dateUpload = photo.dateUpload
                }
            info
        }
    }

    override suspend fun getPhotoInfo(id: Long): PhotoInfo {
        val photo = dao.getPhoto(id)
        return photoInfoMapper.mapFromCached(dao.getPhotoInfo(id))
            .apply {
                url = photo.url
                dateUpload = photo.dateUpload
            }
    }

    override suspend fun savePhotos(photos: List<Photo>) {
        dao.savePhotos(
            *photos.map {
                photoMapper.mapToCached(it)
            }.toTypedArray()
        )
    }

    override suspend fun savePhotoInfo(info: PhotoInfo) {
        dao.savePhotoInfo(photoInfoMapper.mapToCached(info))
    }

    override suspend fun updateAllPhotos(photosToInsert: List<Photo>, photosToUpdate: List<Photo>, photosToDelete: List<Photo>) {
        dao.updateAllPhotos(
            photosToInsert.map {
                photoMapper.mapToCached(it)
            }, photosToUpdate.map {
                photoMapper.mapToCached(it)
            }, photosToDelete.map {
                photoMapper.mapToCached(it)
            })
    }
}
