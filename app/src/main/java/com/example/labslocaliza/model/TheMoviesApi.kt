package com.example.labslocaliza.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMoviesApi {

    @GET("3/movie/popular")
    fun listPopular(
        @Query("api_key") apiKey: String = ApiConsts.API_KEY,
        @Query("language") language: String = "pt-BR",
        @Query("page") page: Int = 1
    ): Call<ListPaginadaMovie>

    @GET("3/movie/{idMovieURL}")
    fun getMovieById(
        @Path("idMovieURL") id: Int,
        @Query("api_key") apiKey: String = ApiConsts.API_KEY,
        @Query("language") language: String = "pt-BR",
    ): Call<MovieModel>

    @GET("3/movie/{movie_id}")
    fun getMovieVideo(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = ApiConsts.API_KEY,
        @Query("language") language: String = "pt-BR",
    ): Call<ListaVideos>


}

