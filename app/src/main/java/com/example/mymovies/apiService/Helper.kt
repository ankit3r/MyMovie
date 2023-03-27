package com.example.mymovies.apiService

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Helper {
    fun getInstance(baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
