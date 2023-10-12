package com.chb.flickrphotosoftheday.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiServiceFactory {

    fun create(isDebug: Boolean, baseUrl: String): PhotosApiService {
        val retrofit = createRetrofit(isDebug, baseUrl)
        return retrofit.create(PhotosApiService::class.java)
    }

    private fun createRetrofit(isDebug: Boolean, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(createOkHttpClient(createLoggingInterceptor(isDebug)))
            .addConverterFactory(createGsonConverterFactory())
            .build()
    }

    private fun createGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    private fun createOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(OK_HTTP_TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    private fun createLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (isDebug) {
                HttpLoggingInterceptor.Level.BASIC
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    private const val OK_HTTP_TIMEOUT = 60L
}
