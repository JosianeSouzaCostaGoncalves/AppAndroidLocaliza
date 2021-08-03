package com.example.labslocaliza.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.labslocaliza.databinding.MovieItemBinding
import com.example.labslocaliza.model.MovieModel

class MoviesViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MoviesAdapter(val onMovieClick: (Int) -> Unit) : RecyclerView.Adapter<MoviesViewHolder>() {
    private val movieListActivity: MutableList<MovieModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movieListActivity[position]
        Glide.with(holder.binding.root)
            .load("https://image.tmdb.org/t/p/w500${item.poster_path}")
            .into(holder.binding.posterId)
        holder.binding.posterId.setOnClickListener { onMovieClick(item.id) }
    }

    override fun getItemCount(): Int {
        return movieListActivity.size
    }

    fun addItemList(list: List<MovieModel>) {
        movieListActivity.clear()
        movieListActivity.addAll(list)
        notifyDataSetChanged()
    }
}