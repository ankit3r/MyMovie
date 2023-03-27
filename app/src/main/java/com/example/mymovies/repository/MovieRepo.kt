package com.example.mymovies.repository

import com.example.mymovies.apiService.ApiCall
import com.example.mymovies.models.movieModel.MovieModel
import com.example.mymovies.models.tvShowModel.TvShowModel
import com.example.mymovies.utils.ResponseData

class MovieRepo(private val movieApi: ApiCall, private val apiKey: String) {
    suspend fun getPopularMovies(page: Int = 1): ResponseData<MovieModel> {
        return try {
            val response = movieApi.getPopularMovies(apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseData.Success(it)
                } ?: ResponseData.Error("Response body is null")
            } else {
                ResponseData.Error("Failed to retrieve popular movies: ${response.code()}")
            }
        } catch (e: Exception) {
            ResponseData.Error("Failed to retrieve popular movies: ${e.message}")
        }
    }

    suspend fun getTrendingMovies(page: Int = 1): ResponseData<MovieModel> {
        return try {
            val response = movieApi.getTrendingMovies(apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseData.Success(it)
                } ?: ResponseData.Error("Response body is null")
            } else {
                ResponseData.Error("Failed to retrieve popular movies: ${response.code()}")
            }
        } catch (e: Exception) {
            ResponseData.Error("Failed to retrieve popular movies: ${e.message}")
        }
    }

    suspend fun getTopRatedMovies(page: Int = 1): ResponseData<MovieModel> {
        return try {
            val response = movieApi.getTopRatedMovies(apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseData.Success(it)
                } ?: ResponseData.Error("Response body is null")
            } else {
                ResponseData.Error("Failed to retrieve popular movies: ${response.code()}")
            }
        } catch (e: Exception) {
            ResponseData.Error("Failed to retrieve popular movies: ${e.message}")
        }
    }

    suspend fun getPopularTvShow(page: Int = 1): ResponseData<TvShowModel> {
        return try {
            val response = movieApi.getPopularTvShow(apiKey, page = page)
            if (response.isSuccessful) {
                response.body()?.let {
                    ResponseData.Success(it)
                } ?: ResponseData.Error("Response body is null")
            } else {
                ResponseData.Error("Failed to retrieve popular movies: ${response.code()}")
            }
        } catch (e: Exception) {
            ResponseData.Error("Failed to retrieve popular movies: ${e.message}")
        }
    }

}