package com.example.labslocaliza.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieModelDao {
    @Insert
    fun insertFavorite(movie: MovieModel)

    @Delete
    fun deleteFavorite(movie: MovieModel)

    @Query("select * from favorites")
    fun getAllFavorite() :List<MovieModel>
}
