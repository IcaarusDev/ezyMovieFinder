package com.icaarusdev.ezymoviefinder.model

import androidx.room.*

@Dao
interface MovieDao {
    @Insert
    suspend fun insertAll(vararg movies: Movie): List<Long>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()

    @Query("SELECT * FROM movies")
    suspend fun getAll(): List<Movie>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    suspend fun getMovie(movieId: Int): Movie

    @Query("SELECT * FROM movies WHERE title LIKE :title")
    suspend fun searchMovieByTitle(title: String): Movie

    @Query("UPDATE movies SET favorite=:favorite WHERE id=:movie_id")
    suspend fun updateFavorite(movie_id: Int,favorite: Int)

}