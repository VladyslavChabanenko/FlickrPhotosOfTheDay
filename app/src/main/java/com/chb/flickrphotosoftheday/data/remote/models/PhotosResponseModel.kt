package com.chb.flickrphotosoftheday.data.remote.models

import com.google.gson.annotations.SerializedName

data class PhotosResponseModel(
    @SerializedName("photos")
    val photosModel: PhotosModel
)
