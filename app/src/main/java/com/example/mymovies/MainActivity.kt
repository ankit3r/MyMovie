package com.example.mymovies

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.utils.MyApplication
import com.example.mymovies.viewModel.ApiFactry
import com.example.mymovies.viewModel.ApiViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var apiViewModel: ApiViewModel
    private lateinit var progressBar: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar()

        val myRepo = (application as MyApplication).movieRepo
        apiViewModel = ViewModelProvider(this,ApiFactry(myRepo))[ApiViewModel::class.java]
        binding.getData.setOnClickListener {
//            apiViewModel.getPopularMovies()
//            apiViewModel.getTrendingMovies()
//            apiViewModel.getTopRatedMovies()
            apiViewModel.getPopularTvShow()
        }
        apiViewModel.isLoading.observe(this){
            if (it) progressBar.show() else progressBar.dismiss()
        }
//        apiViewModel.movieList.observe(this){
//            Log.d("ANKIT",it.results.toString())
//            binding.getData.text = it.results.toString()
//
//        }
        apiViewModel.tvList.observe(this){
            Log.d("ANKIT",it.results.toString())
            binding.getData.text = it.results.toString()
        }

    }
    private fun progressBar() {
        val builder = AlertDialog.Builder(this)
        val inflater = layoutInflater
        val view = inflater.inflate(R.layout.custome_progress_bar, null)
        builder.setView(view)
        builder.setCancelable(false)
        progressBar = builder.create()

    }
}