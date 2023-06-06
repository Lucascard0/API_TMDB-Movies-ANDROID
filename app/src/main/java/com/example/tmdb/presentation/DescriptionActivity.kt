package com.example.tmdb.presentation

import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.tmdb.R
import com.example.tmdb.databinding.ActivityDescriptionBinding
import com.example.tmdb.domain.PopularMovies

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    companion object{

        const val titleDescription = "extra_titleDescription"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras

        if(extras != null){
            populateDetails(extras)
        } else{
            finish()
        }
    }

    private fun populateDetails(extras: Bundle){

        val movie = extras.getSerializable(titleDescription) as PopularMovies

        binding.backdropPath.visibility = View.VISIBLE
        Glide.with(this).load("https://image.tmdb.org/t/p/w1280${movie.backdropPath}")
            .transform(CenterCrop())
            .into(binding.backdropPath)

        binding.titleDescription.text = movie.title
        binding.overviewDescription.text = movie.description
        binding.average.text = movie.voteAverage.toString()
    }

}