package com.icaarusdev.ezymoviefinder.model

import com.google.gson.annotations.SerializedName

data class GetMoviesDetailsResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("backdrop_path")
    val backdrop_path: String,

    @SerializedName("release_date")
    val release_date: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("vote_average")
    val vote_average: String
)