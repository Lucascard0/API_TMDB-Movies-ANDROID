package com.example.tmdb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tmdb.data.remote.MoviesAPI
import com.example.tmdb.data.remote.response.PopularMoviesResponse
import com.example.tmdb.domain.PopularMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel (private val moviesAPI: MoviesAPI) : ViewModel() {


    private val _popularMovies = MutableLiveData<List<PopularMovies>>()
    //popularMovies se torna basicamente uma cópia de _popularMovies (privada), apenas por quesito de segurança para que a variável principal não seja alterada
    val popularMovies: LiveData<List<PopularMovies>> get() = _popularMovies

    private val _popularMoviesErrorResponse = MutableLiveData<String>()
    val popularMoviesErrorPesponse: LiveData<String?> get() = _popularMoviesErrorResponse //String com interrogação significa que pode retornar nulo

    //Uma função suspensa significa que ela pode ser uma função Assíncrona, podendo ser chamadas em momentos diferentes sem travar a Thread principal
    suspend fun getPopularMovies() = withContext(Dispatchers.Main){
        val call: Call<PopularMoviesResponse> = moviesAPI.getPopularMovies()
        call.enqueue(
            object : Callback<PopularMoviesResponse>{
                override fun onResponse(
                    call: Call<PopularMoviesResponse>,
                    response: Response<PopularMoviesResponse>
                ) {
                    if(response.isSuccessful){
                        _popularMovies.value = response.body()?.popularMovies?.map { it.toPopularMovies() }
                    }
                }

                override fun onFailure(call: Call<PopularMoviesResponse>, error: Throwable) {
                    _popularMoviesErrorResponse.value = error.message
                }

            }
        )
    }

}