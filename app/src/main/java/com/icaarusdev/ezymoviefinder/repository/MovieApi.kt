package com.icaarusdev.ezymoviefinder.repository

import com.icaarusdev.ezymoviefinder.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    companion object{
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = BuildConfig.TMDB_ACCESS_KEY
    }

    @Headers("Accept-Version: v1", "")
    @GET("/movie/popular")
    suspend fun searchFilms(
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String,
    ): MovieResponse
}