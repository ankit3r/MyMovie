package com.example.mymovies.apiService

import com.example.mymovies.models.movieModel.MovieModel
import com.example.mymovies.models.tvShowModel.TvShowModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCall {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieModel>

    @GET("trending/movie/week")
    suspend fun getTrendingMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieModel>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<MovieModel>

    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Response<TvShowModel>

}