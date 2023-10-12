package com.chb.flickrphotosoftheday.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.chb.flickrphotosoftheday.data.local.dao.PhotosDao
import com.chb.flickrphotosoftheday.data.local.models.PhotoDBEntity
import com.chb.flickrphotosoftheday.data.local.models.PhotoInfoDBEntity
import com.chb.flickrphotosoftheday.data.local.utils.CacheConstants
import com.chb.flickrphotosoftheday.data.local.utils.Migrations

@Database(
    entities = [PhotoDBEntity::class, PhotoInfoDBEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false
)
abstract class PhotosDatabase : RoomDatabase() {

    abstract fun dao(): PhotosDao

    companion object {
        @Volatile
        private var INSTANCE: PhotosDatabase? = null

        fun getInstance(context: Context): PhotosDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            PhotosDatabase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}
