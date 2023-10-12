package com.chb.flickrphotosoftheday.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chb.flickrphotosoftheday.data.local.utils.CacheConstants

@Entity(tableName = CacheConstants.PHOTO_INFO_TABLE_NAME)
data class PhotoInfoDBEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("description")
    val description: String
)
