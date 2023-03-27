package com.example.mymovies.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.models.movieModel.MovieModel
import com.example.mymovies.models.tvShowModel.TvShowModel
import com.example.mymovies.repository.MovieRepo
import com.example.mymovies.utils.ResponseData
import kotlinx.coroutines.launch
import kotlin.math.log

class ApiViewModel(private val apiRepo: MovieRepo) : ViewModel() {
    private val _movieList = MutableLiveData<MovieModel>()
    val movieList: LiveData<MovieModel> = _movieList
    private val _tvList = MutableLiveData<TvShowModel>()
    val tvList: LiveData<TvShowModel> = _tvList
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    val imgUrl = "https://image.tmdb.org/t/p/original"


    fun getPopularMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = apiRepo.getPopularMovies(1)
            _isLoading.value = false
            when (response) {
                is ResponseData.Success -> {
                    _movieList.value = response.data
                }
                is ResponseData.Error -> {
                    _error.value = response.message
                    Log.e("ANKIT",response.message)
                }
            }
        }
    }
    fun getTrendingMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = apiRepo.getTrendingMovies(1)
            _isLoading.value = false
            when (response) {
                is ResponseData.Success -> {
                    _movieList.value = response.data
                }
                is ResponseData.Error -> {
                    _error.value = response.message
                    Log.e("ANKIT",response.message)
                }
            }
        }
    }
    fun getTopRatedMovies() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = apiRepo.getTopRatedMovies(1)
            _isLoading.value = false
            when (response) {
                is ResponseData.Success -> {
                    _movieList.value = response.data
                }
                is ResponseData.Error -> {
                    _error.value = response.message
                    Log.e("ANKIT",response.message)
                }
            }
        }
    }
    fun getPopularTvShow() {
        viewModelScope.launch {
            _isLoading.value = true
            val response = apiRepo.getPopularTvShow(1)
            _isLoading.value = false
            when (response) {
                is ResponseData.Success -> {
                    _tvList.value = response.data
                }
                is ResponseData.Error -> {
                    _error.value = response.message
                    Log.e("ANKIT",response.message)
                }
            }
        }
    }

}