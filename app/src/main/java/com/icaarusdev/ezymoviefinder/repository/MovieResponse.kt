package com.icaarusdev.ezymoviefinder.repository

import com.icaarusdev.ezymoviefinder.model.Movie

data class MovieResponse(
    val results: List<Movie>
)