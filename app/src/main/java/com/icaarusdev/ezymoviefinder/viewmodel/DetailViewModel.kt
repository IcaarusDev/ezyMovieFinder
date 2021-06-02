package com.icaarusdev.ezymoviefinder.viewmodel

import android.app.Application
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import java.util.*

class DetailViewModel(application: Application) : BaseViewModel(application) {

    val movies = MutableLiveData<List<Movie>>()

    val titleTxt = MutableLiveData<String>()
    val overviewTxt = MutableLiveData<String>()
    val releaseDataTxt = MutableLiveData<String>()
    val voteAverageTxt = MutableLiveData<String>()
    val backdropPathTxt = MutableLiveData<String>()
    val favoriteImage = MutableLiveData<Boolean>()


    fun refreshData(movieId: Int) {
        fetchFromRemote(movieId)
        checkFavorite(movieId)
    }

    private fun checkFavorite(movie_id: Int){
        launch {
            val dao: MovieDao = MovieDatabase(getApplication()).movieDao()
            val result: List<Movie> = dao.getMovie(movie_id)

            favoriteImage.value = result[0].favorite != 1
        }
    }

    private fun fetchFromRemote(movieId: Int) {
        MoviesApiService.getMovieDetails(
            onSuccess = ::onNowPlayingMoviesFetched,
            onError = ::onError,
            movieId = movieId
        )
    }

    private fun onNowPlayingMoviesFetched(title: String, backdrop_path: String,
                                          release_date: String,overview: String,vote_average:String) {
        titleTxt.value = title
        overviewTxt.value = overview
        releaseDataTxt.value = "Release Date: ${release_date}"
        voteAverageTxt.value = "Vote Average: ${vote_average}"
        backdropPathTxt.value = backdrop_path
    }

    private fun onError() {
        Log.d("remoteResponse", "An error occured while loading data from remote...")
    }

    fun updateFavorite(movie_id: Int) {
        launch {
            val dao: MovieDao = MovieDatabase(getApplication()).movieDao()
            val result: List<Movie> = dao.getMovie(movie_id)

            Log.d("testeFavorites","${result[0].favorite}")

            if (result[0].favorite == 1) {
                dao.updateFavorite(movie_id, 0)
                favoriteImage.value = false
            } else {
                dao.updateFavorite(movie_id, 1)
                favoriteImage.value = true
            }
        }
    }


}