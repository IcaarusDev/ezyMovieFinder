package com.icaarusdev.ezymoviefinder.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.model.MoviesApi
import com.icaarusdev.ezymoviefinder.model.MoviesApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel : ViewModel() {

    private val moviesService = MoviesApiService

    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshData() {
        fetchFromRemote()
    }

    private fun fetchFromRemote() {
        loading.value = true
        MoviesApiService.getNowPlaying(
            onSuccess = ::onNowPlayingMoviesFetched,
            onError = ::onError
        )
    }

    private fun onNowPlayingMoviesFetched(moviesList: List<Movie>) {
        Log.d("remoteResponse", "${moviesList}")
        movies.value = moviesList
        moviesLoadError.value = false
        loading.value = false

    }

    private fun onError() {
        moviesLoadError.value = true
        loading.value = false
        Log.d("remoteResponse", "An error occured while loading data from remote...")
    }

    override fun onCleared() {
        super.onCleared()
    }
}