package com.icaarusdev.ezymoviefinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.icaarusdev.ezymoviefinder.data.MovieRepository

class ListViewModel @ViewModelInject constructor(
    private val repository: MovieRepository
) : ViewModel() {

}