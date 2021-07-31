package com.example.labslocaliza.model

import com.example.labslocaliza.model.ApiConsts.API_KEY
import retrofit2.Call
import retrofit2.http.GET

interface TheMoviesApi {
    @GET("3/movie/popular?api_key=$API_KEY&language=pt-BR&page=1")
    fun listPopular(): Call<ListPaginadaMovie>
}