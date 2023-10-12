package com.chb.flickrphotosoftheday.di

import com.chb.flickrphotosoftheday.data.remote.api.PhotosApiService
import com.chb.flickrphotosoftheday.data.remote.api.ApiServiceFactory
import com.chb.flickrphotosoftheday.config.Config
import com.chb.flickrphotosoftheday.data.source.IPhotosRemoteDataSource
import com.chb.flickrphotosoftheday.data.source.PhotosRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteModule {

    @Provides
    @Singleton
    fun providePhotoService(): PhotosApiService {
        return ApiServiceFactory.create(Config.DEBUG, Config.BASE_URL)
    }

    @Provides
    @Singleton
    fun provideRecentPhotosRemoteDataSource(photosRemoteDataSource: PhotosRemoteDataSource): IPhotosRemoteDataSource {
        return photosRemoteDataSource
    }
}
