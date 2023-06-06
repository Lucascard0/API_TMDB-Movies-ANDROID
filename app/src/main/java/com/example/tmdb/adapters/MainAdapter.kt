package com.example.tmdb.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.tmdb.R
import com.example.tmdb.data.remote.dto.PopularMoviesDto
import com.example.tmdb.databinding.ActivityDescriptionBinding
import com.example.tmdb.databinding.ResItemLiveBinding
import com.example.tmdb.domain.PopularMovies
import com.example.tmdb.presentation.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainAdapter(private val onItemClicked: (PopularMovies) -> Unit) :
    RecyclerView.Adapter<MainViewHolder>() {

   // lateinit var onItemClicked : DetailMovies
    private var lives = mutableListOf<PopularMovies>()

    fun setLiveList(lives: List<PopularMovies>){
        this.lives = lives.toMutableList()
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ResItemLiveBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val live = lives[position]
        holder.bind(live, onItemClicked)
    }

    override fun getItemCount(): Int {
        return lives.size
    }
}

class MainViewHolder(val binding: ResItemLiveBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(live: PopularMovies, onItemClicked: (PopularMovies) -> Unit){
        binding.title.text = live.title
        //binding.overview.text = live.description
        binding.posterPath.toString()

        val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)

        Glide.with(itemView.context).applyDefaultRequestOptions(requestOptions)
            .load("https://image.tmdb.org/t/p/original/" + live.posterPath).into(binding.posterPath)

        binding.root.setOnClickListener {
            onItemClicked(live)
        }
    }
}