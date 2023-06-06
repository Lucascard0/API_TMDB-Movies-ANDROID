package com.example.tmdb.data.remote.dto

import com.example.tmdb.domain.PopularMovies
import com.google.gson.annotations.SerializedName

data class PopularMoviesDto(

    //Definindo os campos que serão requisitados no JSON
    //Como o nome da variável declarada é diferente da no formato JSON, utilizamos o SerializedName para informar isto
    val id: Long,
    val title: String,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,

) {
    fun toPopularMovies() : PopularMovies{
        return PopularMovies(
            id = id,
            title = title,
            description = overview,
            voteAverage = voteAverage,
            posterPath = posterPath,
            backdropPath = backdropPath
        )

    }
}
