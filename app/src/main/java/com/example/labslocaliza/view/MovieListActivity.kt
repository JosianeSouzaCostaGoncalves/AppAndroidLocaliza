package com.example.labslocaliza.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.labslocaliza.databinding.ActivityMovieListBinding
import com.example.labslocaliza.model.MovieRepository

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    private var pageApi: Int = 1
    private lateinit var adapterMovies: MoviesAdapter

    companion object {
        const val ID_MOVIE = "com.example.labslocaliza.view.MovieListActivity.ID_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapterMovies = MoviesAdapter({ id ->
            val pulaTela = Intent(this, DetalhesMovieActivity::class.java)
            pulaTela.putExtra(ID_MOVIE, id)
            startActivity(pulaTela)
        }, { movie, isFavorite ->
            movie.is_favorite = isFavorite
            if (isFavorite) MovieRepository.addFavoritos(this, movie)
            else MovieRepository.deleteFavoritos(this, movie)
        })
        binding.movie.adapter = adapterMovies

       callPopular()

        binding.favoriteBt.setOnClickListener { callFavorite() }
        binding.popularBt.setOnClickListener { callPopular() }
    }
    private fun callPopular(){
        MovieRepository.getPopular { list ->
            adapterMovies.addItemList(list)
            binding.favoriteBt.visibility = View.VISIBLE
            binding.popularBt.visibility = View.GONE
        }
    }



    private fun callFavorite() {
        MovieRepository.getFavoritos(this) { list ->
            adapterMovies.addItemList(list)
            binding.popularBt.visibility = View.VISIBLE
        }
    }
}