package com.icaarusdev.ezymoviefinder.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.model.MoviesApiService
import com.icaarusdev.ezymoviefinder.util.SharedPreferencesHelper
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : BaseViewModel(application) {

    val movies = MutableLiveData<List<Movie>>()

    fun refreshData() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        MoviesApiService.getFromSearch(
            onSuccess = ::onNowPlayingMoviesFetched,
            onError = ::onError,
            query = "Jack+Reacher"
        )
    }

    private fun onNowPlayingMoviesFetched(moviesList: List<Movie>) {
        moviesFromRemote(moviesList)
        Log.d("dataRetrievedSearch", "${moviesList}")
    }

    private fun onError() {
        Log.d("remoteResponse", "An error occured while loading data from remote...")
    }

    private fun moviesFromRemote(moviesList: List<Movie>){
        movies.value = moviesList
    }


}