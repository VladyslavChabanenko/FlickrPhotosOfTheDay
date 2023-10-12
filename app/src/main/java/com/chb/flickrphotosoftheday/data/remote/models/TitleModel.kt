package com.chb.flickrphotosoftheday.data.remote.models

import com.google.gson.annotations.SerializedName

data class TitleModel(
    @SerializedName("_content")
    val content: String
)
