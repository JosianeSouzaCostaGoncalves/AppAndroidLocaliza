package com.example.labslocaliza.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.labslocaliza.databinding.ActivityDetalhesBinding
import com.example.labslocaliza.model.ListaVideos
import com.example.labslocaliza.model.MovieRepository
import com.example.labslocaliza.view.MovieListActivity.Companion.ID_MOVIE

class DetalhesMovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetalhesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetalhesBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val idMovieDetalhes = intent.getIntExtra(ID_MOVIE, -1)

        MovieRepository.getMovieDetalhes({
            binding.sinopseId.text = "Sinopse: ${it.overview}"
            binding.nomeId.text = it.title
            binding.anoId.text = it.release_date.take(4)
            binding.classificacaoId.text = if (it.adult) "+18" else "-18"
            binding.generoId.text = "Gênero: ${it.genres.toString()} "
            Glide.with(binding.root)
                .load("https://image.tmdb.org/t/p/w500${it.poster_path}")
                .into(binding.posterId)

        }, idMovieDetalhes)

//        MovieRepository.getMovieVideo({
//
//            println("Josiane"+ it.size)
//
//        }, idMovieDetalhes)
    }

}

