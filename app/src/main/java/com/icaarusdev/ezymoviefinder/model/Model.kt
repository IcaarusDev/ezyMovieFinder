package com.icaarusdev.ezymoviefinder.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0,

    @ColumnInfo(name = "id")
    @SerializedName("id")
    val id: String?,

    @ColumnInfo(name = "favorite")
    var favorite: Int = 0,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    val overview: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    val backDropPath: String?,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    val releaseDate: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    val voteAverage: String?

)