package com.chb.flickrphotosoftheday.data.remote.mappers

import com.chb.flickrphotosoftheday.data.remote.models.TitleModel
import javax.inject.Inject

class PhotoTitleRemoteMapper @Inject constructor(): RemoteMapper<TitleModel, String> {
    override fun mapFromModel(model: TitleModel): String {
        return model.content
    }
}
