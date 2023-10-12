package com.chb.flickrphotosoftheday.data.remote.models

import com.google.gson.annotations.SerializedName

data class PhotoInfoResponseModel(
    @SerializedName("photo")
    val photoModel: PhotoInfoModel
)
