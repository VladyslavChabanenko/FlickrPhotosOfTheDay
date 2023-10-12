package com.chb.flickrphotosoftheday.di

import com.chb.flickrphotosoftheday.data.repositories.PhotosRepository
import com.chb.flickrphotosoftheday.domain.repositories.IPhotosRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePhotosRepository(repository: PhotosRepository): IPhotosRepository =
        repository
}
