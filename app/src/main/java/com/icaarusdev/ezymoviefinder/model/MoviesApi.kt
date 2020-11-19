package com.icaarusdev.ezymoviefinder.model

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {


    @GET("movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = "6035205f93f624b2a23ed8ebb5f8a075",
        @Query("language") language: String = "pt-BR"
//        @Query("page") page: Int
    ): Call<GetMoviesResponse>


    @GET("search/movie")
    fun getMoviesBySearch(
        @Query("api_key") apiKey: String = "6035205f93f624b2a23ed8ebb5f8a075",
        @Query("query") query: String
    ): Call<GetMoviesResponse>


    @GET("movie/{movie_id}")
    fun getMovieDetailsById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = "6035205f93f624b2a23ed8ebb5f8a075",
        @Query("language") language: String = "pt-BR"
    ): Call<GetMoviesDetailsResponse>
}