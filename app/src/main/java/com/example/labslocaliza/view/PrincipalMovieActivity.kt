package com.example.labslocaliza.view

import android.content.Intent
import android.os.Bundle
import  androidx.appcompat.app.AppCompatActivity
import com.example.labslocaliza.databinding.PrincipalMovieBinding


class PrincipalMovieActivity : AppCompatActivity() {
    private lateinit var binding: PrincipalMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PrincipalMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonEnterId.setOnClickListener { pula() }
    }

    fun pula() {
        val pulaTelaPrincipal = Intent(this, MovieListActivity::class.java)
        startActivity(pulaTelaPrincipal)
    }

}
