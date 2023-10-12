package com.chb.flickrphotosoftheday.data.remote.api

import com.chb.flickrphotosoftheday.config.Config
import com.chb.flickrphotosoftheday.data.remote.models.PhotoInfoResponseModel
import com.chb.flickrphotosoftheday.data.remote.models.PhotosResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosApiService {

    @GET("services/rest/?method=flickr.interestingness.getList&nojsoncallback=1&format=json&extras=url_q,url_l,date_upload")
    suspend fun getRecentPhotos(
        @Query("api_key") apiKey: String = Config.API_KEY,
        @Query("per_page") perPage: Int = Config.PER_PAGE,
        @Query("page") page: Int = Config.PAGES_TO_DISPLAY,
    ): PhotosResponseModel

    @GET("services/rest/?method=flickr.photos.getInfo&nojsoncallback=1&format=json")
    suspend fun getPhotoInfo(
        @Query("api_key") apiKey: String = Config.API_KEY,
        @Query("photo_id") id: Long
    ): PhotoInfoResponseModel
}
