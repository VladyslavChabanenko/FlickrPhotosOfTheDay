package com.chb.flickrphotosoftheday.data.remote.models

import com.google.gson.annotations.SerializedName

data class PhotoInfoModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: TitleModel,
    @SerializedName("description")
    val description: DescriptionModel
)
