package com.example.labslocaliza.model

import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object MovieRepository {
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.themoviedb.org/")
        .build()

    val moviesApi: TheMoviesApi = retrofit.create(TheMoviesApi::class.java)

    fun getPopular(callback: (List<MovieModel>) -> Unit) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.listPopular()
                callApi.enqueue(object : Callback<ListPaginadaMovie> {
                    override fun onResponse(
                        call: Call<ListPaginadaMovie>,
                        response: Response<ListPaginadaMovie>
                    ) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<ListPaginadaMovie>, t: Throwable) {
                    }
                })
            }
        }
    }
}
