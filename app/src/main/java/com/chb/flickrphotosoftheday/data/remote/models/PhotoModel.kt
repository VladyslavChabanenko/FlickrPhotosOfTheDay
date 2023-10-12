package com.chb.flickrphotosoftheday.data.remote.models

import com.google.gson.annotations.SerializedName

data class PhotoModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("secret")
    val secret: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url_q")
    val previewUrl: String,
    @SerializedName("url_l")
    val url: String,
    @SerializedName("dateupload")
    val dateUpload: Long
)
