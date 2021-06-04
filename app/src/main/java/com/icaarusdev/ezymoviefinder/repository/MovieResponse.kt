package com.icaarusdev.ezymoviefinder.repository

import com.icaarusdev.ezymoviefinder.data.Movie

data class MovieResponse(
    val results: List<Movie>
)