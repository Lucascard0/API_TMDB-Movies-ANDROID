package com.example.tmdb.data.remote

import com.example.tmdb.data.remote.dto.PopularMoviesDto
import com.example.tmdb.data.remote.response.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    //Base URL
    @GET("/3/movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") languageBR: String = LANGUAGE


    ) : Call<PopularMoviesResponse>

    companion object {

        val BASE_URL = "https://api.themoviedb.org/"
        val API_KEY = "8814a462567c898e15cec5d9ce177bd6"
        val LANGUAGE = "pt-BR"
    }
}