package com.example.labslocaliza

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.labslocaliza.databinding.MovieItemBinding

class MoviesViewHolder(val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root)

class MoviesAdapter(val onMovieClick: () -> Unit) : RecyclerView.Adapter<MoviesViewHolder>() {
    val movieList: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movieList.get(position)
        // holder.binding.tituloFavoritos.text = item
        holder.binding.laCasaDePapelId.setOnClickListener { onMovieClick() }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addItemList(list: List<String>) {
        movieList.clear()
        movieList.addAll(list)
        notifyDataSetChanged()


    }


}