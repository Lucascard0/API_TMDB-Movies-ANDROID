package com.example.tmdb.domain

data class PopularMovies (
    /* Criado apenas para caso de estudo, caso tivesse muitos campos dentro da requisição da api,
    * e houvesse a necessidade de selecionar apenas alguns campos específicos, assim iria criar
    * um objeto menor, apenas com os campos necessário para utilização
    */

    val id: Long,
    val title: String,
    val description: String,
    val voteAverage: Double,
    val posterPath: String
)
