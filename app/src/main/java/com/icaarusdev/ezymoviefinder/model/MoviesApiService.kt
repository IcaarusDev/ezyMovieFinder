package com.icaarusdev.ezymoviefinder.model

import android.util.Log
import io.reactivex.Single
import io.reactivex.internal.operators.single.SingleDoOnSuccess
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MoviesApiService {
    private val api: MoviesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
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
}