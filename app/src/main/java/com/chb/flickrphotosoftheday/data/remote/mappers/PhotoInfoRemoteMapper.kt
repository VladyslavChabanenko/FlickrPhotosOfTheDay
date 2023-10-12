package com.chb.flickrphotosoftheday.data.remote.mappers

import com.chb.flickrphotosoftheday.data.remote.models.PhotoInfoModel
import com.chb.flickrphotosoftheday.domain.entities.PhotoInfo
import javax.inject.Inject

class PhotoInfoRemoteMapper @Inject constructor(
    private val titleMapper: PhotoTitleRemoteMapper,
    private val descriptionMapper: PhotoDescriptionRemoteMapper
): RemoteMapper<PhotoInfoModel, PhotoInfo> {
    override fun mapFromModel(model: PhotoInfoModel): PhotoInfo {
        return PhotoInfo(
            id = model.id,
            title = titleMapper.mapFromModel(model.title),
            description = descriptionMapper.mapFromModel(model.description)

        )
    }
}
