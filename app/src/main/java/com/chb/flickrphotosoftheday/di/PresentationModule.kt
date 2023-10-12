package com.chb.flickrphotosoftheday.di

import com.chb.flickrphotosoftheday.presentation.async.CoroutineContextProvider
import com.chb.flickrphotosoftheday.presentation.async.ICoroutineContextProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PresentationModule {

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(contextProvider: CoroutineContextProvider): ICoroutineContextProvider =
        contextProvider
}
