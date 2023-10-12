package com.chb.flickrphotosoftheday.data.local.mappers

import com.chb.flickrphotosoftheday.data.local.models.PhotoInfoDBEntity
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import javax.inject.Inject

class PhotoInfoLocalMapper @Inject constructor() : LocalMapper<PhotoInfoDBEntity, PhotoInfo> {
    override fun mapFromCached(dbEntity: PhotoInfoDBEntity): PhotoInfo {
        return PhotoInfo(
            id = dbEntity.id,
            title = dbEntity.title,
            description = dbEntity.description
        )
    }

    override fun mapToCached(entity: PhotoInfo): PhotoInfoDBEntity {
        return PhotoInfoDBEntity(
            id = entity.id,
            title = entity.title,
            description = entity.description
        )
    }
}
