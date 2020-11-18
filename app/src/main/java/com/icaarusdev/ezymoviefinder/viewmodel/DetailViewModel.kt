package com.icaarusdev.ezymoviefinder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.model.Movie

class DetailViewModel : ViewModel() {
    val movieLiveData = MutableLiveData<Movie>()

    fun fetchData(){
        val movieTest1 = Movie("1", "Quando um espião do governo é hospitalizado, " +
                "um taxista de Nova York assume a missão contra a sua vontade, " +
                "com a ajuda de um smoking computadorizado.","","O terno de dois bilhões de dolares","2020-09-27","6.1")

        movieLiveData.value = movieTest1
    }

}