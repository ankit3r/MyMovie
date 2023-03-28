package com.example.mymovies

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.AlertDialog
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.view.animation.TranslateAnimation
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mymovies.databinding.ActivityMainBinding
import com.example.mymovies.fragment.ViewFragment
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
        setSupportActionBar(binding.toolbar)
        progressBar()
        animFun()
        val myRepo = (application as MyApplication).movieRepo
        apiViewModel = ViewModelProvider(this, ApiFactry(myRepo))[ApiViewModel::class.java]
        apiViewModel.isLoading.observe(this) {
            if (it) progressBar.show() else progressBar.dismiss()
        }

        apiViewModel.getPopularMovies()
        apiViewModel.getTrendingMovies()
        apiViewModel.getTopRatedMovies()
        apiViewModel.getPopularTvShow()
        apiViewModel.TranidList.observe(this) {
            setFragment(R.id.fram1, it.results)
        }
        apiViewModel.TopRatdList.observe(this) {
            setFragment(R.id.fram2, it.results)
        }
        apiViewModel.PopularList.observe(this) {
            setFragment(R.id.fram3, it.results)
        }
        apiViewModel.tvList.observe(this) {
            setFragment(R.id.fram4, it.results)
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

    private fun <T> setFragment(fragm: Int, list: List<T>) {
        val myFragment = ViewFragment(list)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(fragm, myFragment)
        fragmentTransaction.commit()
    }

    private fun animFun(){
        val animator3 = ObjectAnimator.ofFloat(binding.view2, "translationX",0f, -900f, 0f, -900f, 0f)
        animator3.duration = 200000
        animator3.repeatCount = ValueAnimator.INFINITE

        val rotationAnimator = ObjectAnimator.ofFloat(binding.view, "rotation", 0f, 360f)
        rotationAnimator.duration = 90000
        rotationAnimator.interpolator = LinearInterpolator()
        rotationAnimator.repeatCount = ObjectAnimator.INFINITE

        val animator = ObjectAnimator.ofFloat(binding.view3, "translationY", 1000f, -320f, 100f, -500f, 200f)
        animator.duration = 90000
        animator.repeatCount = ObjectAnimator.INFINITE
        animator.repeatMode = ObjectAnimator.REVERSE

        val animator2 = ObjectAnimator.ofFloat(binding.view3, "translationX", 0f, 100f, 400f,700f,1000f,500f,700f,50f,0f)
        animator2.duration = 90000
        animator2.repeatCount = ObjectAnimator.INFINITE
        animator2.repeatMode = ObjectAnimator.REVERSE

        val animators = AnimatorSet()
        animators.playTogether(animator, animator2,rotationAnimator,animator3)
        animators.start()

    }



}