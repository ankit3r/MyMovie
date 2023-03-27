package com.example.mymovies.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.repository.MovieRepo

class ApiFactry(private val apiRepo: MovieRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ApiViewModel(apiRepo) as T
    }
}