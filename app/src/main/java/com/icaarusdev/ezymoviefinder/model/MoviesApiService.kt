package com.icaarusdev.ezymoviefinder.model

import android.util.Log
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApiService {
    private val api: MoviesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        api = retrofit.create(MoviesApi::class.java)
    }

    fun getNowPlaying(
        page: Int = 1,
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit
    ) {
        api.getNowPlayingMovies()
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val bodyResponse = response.body()
                        if (bodyResponse != null) {
                            onSuccess.invoke(bodyResponse.moviesList)
                        } else {
                            onError.invoke()
                        }
                    }
                }
            })
    }

    fun getFromSearch(
        onSuccess: (movies: List<Movie>) -> Unit,
        onError: () -> Unit,
        query: String
    ) {
        api.getMoviesBySearch(query = query)
            .enqueue(object : Callback<GetMoviesResponse> {
                override fun onFailure(call: Call<GetMoviesResponse>, t: Throwable) {
                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<GetMoviesResponse>,
                    response: Response<GetMoviesResponse>
                ) {
                    if (response.isSuccessful) {
                        val bodyResponse = response.body()
                        if (bodyResponse != null) {
                            onSuccess.invoke(bodyResponse.moviesList)
                        } else {
                            onError.invoke()
                        }
                    }
                }
            })
    }

    fun getMovieDetails(
        onSuccess: (title:String,
                    backdrop_path:String,
                    release_date:String,
                    overview: String,
                    vote_average: String
                    ) -> Unit,
        onError: () -> Unit,
        movieId: Int
    ) {

        api.getMovieDetailsById(movieId = movieId)
            .enqueue(object : retrofit2.Callback<GetMoviesDetailsResponse> {
                override fun onFailure(call: Call<GetMoviesDetailsResponse>, t: Throwable) {
                    Log.d("getMovieDetails", "error")
                }

                override fun onResponse(
                    call: Call<GetMoviesDetailsResponse>,
                    response: Response<GetMoviesDetailsResponse>
                ) {

                    if (response.isSuccessful) {
                        val bodyResponse = response.body()
                        if (bodyResponse != null) {
                            onSuccess.invoke(bodyResponse.title,
                                bodyResponse.backdrop_path,
                                bodyResponse.release_date,
                                bodyResponse.overview,
                                bodyResponse.vote_average)
                        } else {
                            onError.invoke()
                        }
                    }
                }
            })
    }
}