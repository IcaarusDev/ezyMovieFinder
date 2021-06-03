package com.icaarusdev.ezymoviefinder.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val page: Int,
    val results: MovieResults
) : Parcelable {
    @Parcelize
    data class MovieResults(
        val id: Int,
        val original_title: String,
        val backdrop_path: String
    ):Parcelable
}