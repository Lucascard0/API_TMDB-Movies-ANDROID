package com.example.tmdb.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.tmdb.R
import com.example.tmdb.adapters.MainAdapter
import com.example.tmdb.data.remote.MoviesAPI
import com.example.tmdb.data.remote.dto.PopularMoviesDto
import com.example.tmdb.databinding.ActivityDescriptionBinding
import com.example.tmdb.databinding.ActivityMainBinding
import com.example.tmdb.di.viewModelModule
import com.example.tmdb.domain.PopularMovies
import com.example.tmdb.presentation.DescriptionActivity.Companion.titleDescription
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private val moviesAPI = MoviesAPI
    private val adapter = MainAdapter{
        //Toast.makeText(this, "Teste 1", Toast.LENGTH_LONG).show()

        showMovieDetails(it)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerview.adapter = adapter

        /*
        //Teste apenas para verificarmos no log se as requisições da API estão sendo obtidas
        mainViewModel.popularMovies.observe(this) { popularMovies ->
            popularMovies.forEach {
                Log.i("PopularMovie", it.title)
                Log.i("Teste2", it.description)
                Log.i("poster", it.posterPath)
            }
        }*/

    }

    override fun onStart() {
        super.onStart()

        mainViewModel.popularMovies.observe(this, Observer { lives ->
            adapter.setLiveList(lives)

            /*binding.recyclerview.setOnClickListener {
                startActivity(Intent(
                    this@MainActivity, DescriptionActivity::class.java
                ))
            }*/

        })

    }

    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            mainViewModel.getPopularMovies()
        }
    }

    private fun showMovieDetails(movie: PopularMovies){
        val intent = Intent(this, DescriptionActivity::class.java)

        intent.putExtra(titleDescription, movie)
        startActivity(intent)
    }
}
