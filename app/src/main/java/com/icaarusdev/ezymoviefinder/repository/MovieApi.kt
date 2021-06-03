package com.icaarusdev.ezymoviefinder.repository

import retrofit2.http.Query

interface MovieApi {

    suspend fun searchFilms(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        @Query("region") region: String,
    )
}