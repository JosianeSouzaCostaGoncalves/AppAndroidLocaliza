package com.example.labslocaliza.model

import com.example.labslocaliza.model.ApiConsts.API_KEY
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TheMoviesApi {
    @GET("3/movie/popular?api_key=$API_KEY&language=pt-BR&page=1")
    fun listPopular(): Call<ListPaginadaMovie>

    @GET("3/movie/{idMovieURL}?api_key=$API_KEY&language=pt-BR")
    fun getMovieById(@Path("idMovieURL")id:Int): Call<MovieModel>
}
