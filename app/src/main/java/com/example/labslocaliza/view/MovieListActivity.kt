package com.example.labslocaliza.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.labslocaliza.databinding.ActivityMovieListBinding
import com.example.labslocaliza.model.MovieRepository

class MovieListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding
    companion object{
        const val ID_MOVIE = "com.example.labslocaliza.view.MovieListActivity.ID_MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MoviesAdapter() { id ->
            val pulaTela = Intent(this, DetalhesMovieActivity::class.java)
            pulaTela.putExtra(ID_MOVIE, id)
            startActivity(pulaTela)
        }
        binding.movie.adapter = adapter

        MovieRepository.getPopular { list ->
            adapter.addItemList(list)
        }
    }
}