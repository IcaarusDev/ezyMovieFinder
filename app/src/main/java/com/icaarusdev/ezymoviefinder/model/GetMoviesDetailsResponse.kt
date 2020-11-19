package com.icaarusdev.ezymoviefinder.model

import com.google.gson.annotations.SerializedName

data class GetMoviesDetailsResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String

)