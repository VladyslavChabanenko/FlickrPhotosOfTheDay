package com.chb.flickrphotosoftheday.domain.entities

import java.io.Serializable

data class PhotoInfo(
    val id: Long,
    val title: String,
    val description: String,
    var url: String = "",
    var dateUpload: Long = 0L
) : Serializable
