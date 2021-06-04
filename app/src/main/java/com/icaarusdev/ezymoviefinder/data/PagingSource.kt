package com.icaarusdev.ezymoviefinder.data

import androidx.paging.PagingSource
import com.icaarusdev.ezymoviefinder.repository.MovieApi
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGING_INDEX = 1

class PagingSource(
    private val movieApi: MovieApi,
) : PagingSource<Int, Movie>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGING_INDEX
        val response = movieApi.searchFilms("", position, "")
        val films = response.results

        return try {
            LoadResult.Page(
                data = films,
                prevKey = if (position == STARTING_PAGING_INDEX) null else position - 1,
                nextKey = if (films.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}