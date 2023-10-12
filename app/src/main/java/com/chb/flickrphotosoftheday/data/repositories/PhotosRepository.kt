package com.chb.flickrphotosoftheday.data.repositories

import com.chb.flickrphotosoftheday.data.source.IPhotosLocalDataSource
import com.chb.flickrphotosoftheday.data.source.IPhotosRemoteDataSource
import com.chb.flickrphotosoftheday.domain.entities.Photo
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import com.chb.flickrphotosoftheday.domain.repositories.IPhotosRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosRepository @Inject constructor(
    private val remoteDataSource: IPhotosRemoteDataSource,
    private val localDataSource: IPhotosLocalDataSource
) : IPhotosRepository {

    override suspend fun getPhotos(): Flow<List<Photo>> = flow {
        val photos = localDataSource.getPhotos()
            .ifEmpty {
                val photosFromRemote = remoteDataSource.getPhotos()
                localDataSource.savePhotos(photosFromRemote)
                photosFromRemote
            }
            .sortedBy { it.dateUpload }
        emit(photos)
        photos.onEach {
            val info = remoteDataSource.getPhotoInfo(it.id)
            localDataSource.savePhotoInfo(info)
        }
        updatePhotos()
    }

    override suspend fun getPhotoInfoList(): Flow<List<PhotoInfo>> = flow {
        val list = localDataSource.getPhotoInfoList().sortedBy { it.dateUpload }
        emit(list)
    }

    override suspend fun getPhotoInfo(id: Long): Flow<PhotoInfo> = flow {
        val photoInfo = localDataSource.getPhotoInfo(id)
        emit(photoInfo)
    }

    override suspend fun updatePhotos() {
        buildDBChanges(localDataSource.getPhotos(), remoteDataSource.getPhotos())
            .also { (dataToAdd, dataToUpdate, dataToDelete) ->
                localDataSource.updateAllPhotos(dataToAdd, dataToUpdate, dataToDelete)
            }
    }

    private fun buildDBChanges(newData: List<Photo>, oldData: List<Photo>): Triple<ArrayList<Photo>, ArrayList<Photo>, ArrayList<Photo>> {
        val dataToAdd = arrayListOf<Photo>()
        val dataToUpdate = arrayListOf<Photo>()
        val dataToRemove = arrayListOf<Photo>()
        dataToRemove.addAll(oldData)
        newData.forEach { newItem ->
            val oldItem = dataToRemove.find { oldItem ->
                oldItem.id == newItem.id
            }
            if (oldItem == null) {
                dataToAdd.add(newItem)
            } else {
                dataToRemove.remove(oldItem)
                dataToUpdate.add(oldItem)
            }
        }
        return Triple(dataToAdd, dataToUpdate, dataToRemove)
    }
}
