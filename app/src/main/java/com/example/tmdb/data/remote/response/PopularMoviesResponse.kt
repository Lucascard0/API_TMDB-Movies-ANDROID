package com.example.tmdb.data.remote.response

import com.example.tmdb.data.remote.dto.PopularMoviesDto
import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    /* Quando for deserializar, vamos chamar o objeto response "PopularMoviesResponse", que vai ter uma variável
    * que referencia results, e essa variável vai ter uma lista de PopularMoviesDto
    */
    @SerializedName("results")
    val popularMovies : List<PopularMoviesDto>
)
