package com.chb.flickrphotosoftheday.data.remote.mappers

import com.chb.flickrphotosoftheday.data.remote.models.DescriptionModel
import javax.inject.Inject

class PhotoDescriptionRemoteMapper @Inject constructor(): RemoteMapper<DescriptionModel, String> {
    override fun mapFromModel(model: DescriptionModel): String {
        return model.content
    }
}
