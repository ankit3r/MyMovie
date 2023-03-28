package com.example.mymovies.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.palette.graphics.Palette
import com.example.mymovies.models.movieModel.Result
import com.example.mymovies.models.tvShowModel.TvShowResult
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.example.mymovies.databinding.LayoutMovieCardBinding

class ViewAdapter<T>(private val list: List<T>, private val context: Context) :
    RecyclerView.Adapter<ViewAdapter<T>.MyViewHolder>() {

    inner class MyViewHolder(private val binding: LayoutMovieCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: T) {
            when (data) {
                is Result -> {
                    binding.apply {
                        setImage(data.poster_path)
                        txtName.text = data.title
                    }
                }
                is TvShowResult -> {
                    binding.apply {
                        setImage(data.poster_path)
                        txtName.text = data.name
                    }
                }
            }

        }

        private fun isColorDark(color: Int): Boolean {
            val darkness =
                1 - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(
                    color
                )) / 255
            return darkness >= 0.5
        }

        private fun setImage(url: String) {
            Glide.with(context)
                .asBitmap()
                .load("https://image.tmdb.org/t/p/w500$url")
                .listener(object :RequestListener<Bitmap>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Bitmap?,
                        model: Any?,
                        target: Target<Bitmap>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.progressBar.visibility = View.GONE
                        return false
                    }

                })
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {
                        Palette.from(resource).generate { palette ->
                            val dominantColor =
                                palette?.dominantSwatch?.rgb ?: Color.WHITE
                            binding.card.setCardBackgroundColor(
                                ColorStateList.valueOf(
                                    dominantColor
                                )
                            )
                            val textColor =
                                if (isColorDark(dominantColor)) Color.WHITE else Color.BLACK
                            binding.txtName.setTextColor(textColor)
                        }
                        binding.imgPoster.setImageBitmap(resource)
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                })

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = LayoutMovieCardBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }
}
