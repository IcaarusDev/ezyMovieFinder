package com.icaarusdev.ezymoviefinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.model.Movie

class ListViewModel: ViewModel() {
    val movies = MutableLiveData<List<Movie>>()
    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refreshData(){
        val movieTest1 = Movie("1", "Quando um espião do governo é hospitalizado, " +
                "um taxista de Nova York assume a missão contra a sua vontade, " +
                "com a ajuda de um smoking computadorizado.","","O terno de dois bilhões de dolares","2020-09-27","6.1")

        val movieTest2 = Movie("2", "Quando um espião do governo é hospitalizado, " +
                "um taxista de Nova York assume a missão contra a sua vontade, " +
                "com a ajuda de um smoking computadorizado.","","O terno de dois bilhões de dolares","2020-09-27","6.1")

        val movieList: ArrayList<Movie> = arrayListOf<Movie>(movieTest1,movieTest2)

        movies.value = movieList
        moviesLoadError.value = false
        loading.value = false
    }
}