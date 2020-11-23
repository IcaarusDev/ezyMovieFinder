package com.icaarusdev.ezymoviefinder.viewmodel

import android.app.Application
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.model.*
import com.icaarusdev.ezymoviefinder.util.SharedPreferencesHelper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val favoriteImage = MutableLiveData<Boolean>()

    fun refreshData() {
        val updateTime = prefHelper.getUpdateTime()
        if (updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime) {
            fetchFromDb()
        } else {
            fetchFromRemote()
        }
    }

    fun refreshFromCache() {
        fetchFromRemote()
    }

    private fun fetchFromDb() {
        loading.value = true
        launch {
            val movies: List<Movie> = MovieDatabase(getApplication()).movieDao().getAll()
            moviesFromRemote(movies)
            Log.d("dataRetrievedFrom", "Movies from database")
        }
    }

    private fun fetchFromRemote() {
        loading.value = true
        MoviesApiService.getNowPlaying(
            onSuccess = ::onNowPlayingMoviesFetched,
            onError = ::onError
        )
    }

    private fun onNowPlayingMoviesFetched(moviesList: List<Movie>) {
        storeRemoteDataLocally(moviesList)
        Log.d("dataRetrievedFrom", "Movies from endpoint")
    }

    private fun onError() {
        moviesLoadError.value = true
        loading.value = false
        Log.d("remoteResponse", "An error occured while loading data from remote...")
    }

    private fun moviesFromRemote(moviesList: List<Movie>) {
        movies.value = moviesList
        moviesLoadError.value = false
        loading.value = false
    }

    private fun storeRemoteDataLocally(movieList: List<Movie>) {
        launch {
            val dao: MovieDao = MovieDatabase(getApplication()).movieDao()
            dao.deleteAll()

            val result: List<Long> = dao.insertAll(*movieList.toTypedArray())
            var i = 0
            while (i < movieList.size) {
                movieList[i].uuid = result[i].toInt()
                ++i
            }
            moviesFromRemote(movieList)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }

    fun updateFavorite(movie_id: Int) {
        launch {
            val dao: MovieDao = MovieDatabase(getApplication()).movieDao()
            val result: List<Movie> = dao.getMovie(movie_id)

            if (result[0].favorite == 1) {
                dao.updateFavorite(movie_id, 0)
                favoriteImage.value = false
            } else {
                dao.updateFavorite(movie_id, 1)
                favoriteImage.value = true
            }
        }
    }

    private fun setOverviewTextLimit(){

    }
}