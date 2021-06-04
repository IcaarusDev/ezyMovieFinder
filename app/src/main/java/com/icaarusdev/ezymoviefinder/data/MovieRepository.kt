package com.icaarusdev.ezymoviefinder.data

import com.icaarusdev.ezymoviefinder.repository.MovieApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val movieApi: MovieApi){
}