package com.example.labslocaliza

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.labslocaliza.databinding.ActivityMovieListBinding

class MovieList : AppCompatActivity() {
    private lateinit var binding: ActivityMovieListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = MoviesAdapter() {
            val pulaTela = Intent(this, MainActivity::class.java)
            startActivity(pulaTela)
        }
        binding.movie.adapter = adapter
        val list = List(10) { "teste $it" }
        adapter.addItemList(list)

    }

}