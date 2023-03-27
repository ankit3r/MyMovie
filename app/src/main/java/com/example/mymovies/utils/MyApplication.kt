package com.example.mymovies.utils

import android.app.Application
import com.example.mymovies.apiService.ApiCall
import com.example.mymovies.apiService.Helper
import com.example.mymovies.repository.MovieRepo
import java.util.*

class MyApplication : Application() {
    lateinit var movieRepo: MovieRepo
    lateinit var apiKey: String
    override fun onCreate() {
        super.onCreate()
        val assetManager = assets
        val inputStream = assetManager.open("config.properties")
        val properties = Properties()
        properties.load(inputStream)
        val baseUrl = properties.getProperty("BASE_URL")
        apiKey = properties.getProperty("API_KEY")
        initialize(baseUrl, apiKey)
    }

    private fun initialize(baseUrl: String, apiKey: String) {
        val movieApi = Helper.getInstance(baseUrl).create(ApiCall::class.java)
        movieRepo = MovieRepo(movieApi, apiKey)
    }
}