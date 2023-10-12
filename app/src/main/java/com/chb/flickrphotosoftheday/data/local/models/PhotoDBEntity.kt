package com.chb.flickrphotosoftheday.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chb.flickrphotosoftheday.data.local.utils.CacheConstants

@Entity(tableName = CacheConstants.PHOTOS_TABLE_NAME)
data class PhotoDBEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("previewUrl")
    val previewUrl: String,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("dateUpload")
    val dateUpload: Long
)
