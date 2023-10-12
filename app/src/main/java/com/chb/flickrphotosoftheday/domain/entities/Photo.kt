package com.chb.flickrphotosoftheday.domain.entities

data class Photo(
    val id: Long,
    val title: String,
    val previewUrl: String,
    val url: String,
    val dateUpload: Long
)
