package com.chb.flickrphotosoftheday.data.local.mappers

import com.chb.flickrphotosoftheday.data.local.models.PhotoDBEntity
import com.chb.flickrphotosoftheday.domain.entities.Photo
import javax.inject.Inject

class PhotoLocalMapper @Inject constructor() : LocalMapper<PhotoDBEntity, Photo> {
    override fun mapFromCached(dbEntity: PhotoDBEntity): Photo {
        return Photo(
            id = dbEntity.id,
            title = dbEntity.title,
            previewUrl = dbEntity.previewUrl,
            url = dbEntity.url,
            dateUpload = dbEntity.dateUpload
        )
    }

    override fun mapToCached(entity: Photo): PhotoDBEntity {
        return PhotoDBEntity(
            id = entity.id,
            title = entity.title,
            previewUrl = entity.previewUrl,
            url = entity.url,
            dateUpload = entity.dateUpload
        )
    }
}
