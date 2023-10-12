package com.chb.flickrphotosoftheday.di

import android.content.Context
import com.chb.flickrphotosoftheday.data.local.dao.PhotosDao
import com.chb.flickrphotosoftheday.data.local.database.PhotosDatabase
import com.chb.flickrphotosoftheday.data.source.IPhotosLocalDataSource
import com.chb.flickrphotosoftheday.data.source.PhotosLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(@ApplicationContext context: Context): PhotosDatabase {
        return PhotosDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providePhotosDao(database: PhotosDatabase): PhotosDao {
        return database.dao()
    }

    @Provides
    @Singleton
    fun provideLocalDataSource(localDataSource: PhotosLocalDataSource): IPhotosLocalDataSource {
        return localDataSource
    }
}
