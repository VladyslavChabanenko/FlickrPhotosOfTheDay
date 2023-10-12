package com.chb.flickrphotosoftheday.data.remote.mappers

import com.chb.flickrphotosoftheday.data.remote.models.PhotoModel
import com.chb.flickrphotosoftheday.domain.entities.Photo
import javax.inject.Inject

class PhotosRemoteMapper @Inject constructor() : RemoteMapper<PhotoModel, Photo> {
    override fun mapFromModel(model: PhotoModel): Photo {
        return Photo(
            id = model.id,
            title = model.title,
            previewUrl = model.previewUrl,
            url = model.url,
            dateUpload = model.dateUpload
        )
    }
}
