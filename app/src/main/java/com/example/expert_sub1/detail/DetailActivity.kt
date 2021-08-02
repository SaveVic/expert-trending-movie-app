package com.example.expert_sub1.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.expert_sub1.R
import com.example.expert_sub1.core.domain.model.Movie
import com.example.expert_sub1.core.utils.DataHelper
import com.example.expert_sub1.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private var statusFavorite = false
    private var initFavorite = true

    private lateinit var binding: ActivityDetailBinding

    private val detailMovieViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailMovie = intent.getParcelableExtra<Movie>(EXTRA_DATA)
        showDetailMovie(detailMovie)
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let {
            supportActionBar?.title = detailMovie.title
            binding.content.detailDescription.text = detailMovie.description
            val rating = "${detailMovie.rating} (${detailMovie.rateCount} user)"
            binding.content.detailRating.text = rating
            binding.content.detailType.text = detailMovie.type
            Glide.with(this@DetailActivity)
                .load(DataHelper.rawImagePath(detailMovie.imagePath))
                .into(binding.detailImage)

            detailMovieViewModel.checkFavorite(detailMovie).observe(this, {
                if (initFavorite) {
                    statusFavorite = detailMovie.isFavorite
                    setStatusFavorite(statusFavorite)
                    initFavorite = false
                }
            })
            binding.detailFav.setOnClickListener {
                statusFavorite = !statusFavorite
                detailMovieViewModel.setFavoriteMovie(detailMovie, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.detailFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.detailFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}