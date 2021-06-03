package com.icaarusdev.ezymoviefinder.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application) {

    private var refreshTime = 5 * 60 * 1000 * 1000 * 1000L

    val moviesLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val favoriteImage = MutableLiveData<Boolean>()




}