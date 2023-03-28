package com.example.mymovies.models.tvShowModel

data class TvShowModel(
    val page: Int,
    val results: List<TvShowResult>,
    val total_pages: Int,
    val total_results: Int
)