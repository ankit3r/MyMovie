package com.example.mymovies.apiService

import com.example.mymovies.models.moveCast.Credits
import com.example.mymovies.models.movieDetails.MovieData
import com.example.mymovies.models.movieModel.MovieModel
import com.example.mymovies.models.movieReview.MovieReview
import com.example.mymovies.models.tvCast.TvCredits
import com.example.mymovies.models.tvShowDetails.TvShowData
import com.example.mymovies.models.tvShowModel.TvShowModel
import com.example.mymovies.models.tvShowModel.TvShowResult
import com.example.mymovies.models.tvShowReview.TvShowReview
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    @GET("tv/{tv_id}")
    suspend fun getTvShowById(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<TvShowData>
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<MovieData>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<Credits>

    @GET("tv/{tv_id}/aggregate_credits")
    suspend fun getTvCredits(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<TvCredits>

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReview(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<MovieReview>
    @GET("tv/{tv_id}/reviews")
    suspend fun getTvShowReview(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "en-US"
    ): Response<TvShowReview>
}