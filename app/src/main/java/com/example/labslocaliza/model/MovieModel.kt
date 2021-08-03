package com.example.labslocaliza.model

data class MovieModel(val title: String, val id: Int, val poster_path: String, val overview: String, val video: Boolean, val popularity: Number, val genres: List<GenAPI>)