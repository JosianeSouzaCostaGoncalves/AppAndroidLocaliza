package com.example.labslocaliza.model

import android.content.Context
import androidx.room.Room
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
    var dataBase: AppDataBase? = null

    fun initDataBase(contex: Context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(contex, AppDataBase::class.java, "database_app").build()
        }
    }

    fun addFavoritos(contex: Context, movieModel: MovieModel) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                dataBase?.movieDao()?.insertFavorite(movieModel)
            }
        }
    }

    fun getFavoritos(contex: Context, callback: (List<MovieModel>) -> Unit) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                val listFavorites = dataBase?.movieDao()?.getAllFavorite()
                withContext(Dispatchers.Main) {
                    callback(listFavorites ?: listOf())
                }
            }
        }
    }

    fun deleteFavoritos(contex: Context, movieModel: MovieModel) {
        initDataBase(contex)
        CoroutineScope(GlobalScope.coroutineContext).launch {
            withContext(Dispatchers.IO) {
                dataBase?.movieDao()?.deleteFavorite(movieModel)
            }

        }
    }

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

    fun getMovieDetalhes(callback: (MovieModel) -> Unit, id: Int) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.getMovieById(id)

                callApi.enqueue(object : Callback<MovieModel> {
                    override fun onResponse(
                        call: Call<MovieModel>,
                        response: Response<MovieModel>
                    ) {
                        response.body()?.let {
                            callback(it)
                        }

                    }

                    override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                    }
                })
            }
        }
    }

    fun getMovieVideo(callback: (List<VideoModel>) -> Unit, id: Int) {
        CoroutineScope(GlobalScope.coroutineContext).launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                val callApi = moviesApi.getMovieVideo(id)

                callApi.enqueue(object : Callback<ListaVideos> {
                    override fun onResponse(
                        call: Call<ListaVideos>,
                        response: Response<ListaVideos>
                    ) {
                        callback(response.body()?.results ?: mutableListOf())
                    }

                    override fun onFailure(call: Call<ListaVideos>, t: Throwable) {
                    }
                })
            }
        }
    }
}





