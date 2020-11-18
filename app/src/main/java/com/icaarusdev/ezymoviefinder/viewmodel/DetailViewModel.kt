package com.icaarusdev.ezymoviefinder.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.model.Movie
import com.icaarusdev.ezymoviefinder.model.MovieDatabase
import kotlinx.coroutines.launch
import java.util.*

class DetailViewModel(application: Application) : BaseViewModel(application) {
    val movieLiveData = MutableLiveData<Movie>()

    fun fetchData(id: Int) {
        launch {
            val movie = MovieDatabase(getApplication()).movieDao().getMovie(id)
            movieLiveData.value = movie
        }
    }

}