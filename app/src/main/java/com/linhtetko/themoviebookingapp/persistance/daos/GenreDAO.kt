package com.linhtetko.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.themoviebookingapp.data.vos.moviedb.GenreVO

@Dao
interface GenreDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<GenreVO>)

    @Query("SELECT * FROM genres")
    fun getGenres(): List<GenreVO>

    @Query("DELETE FROM genres")
    fun deleteGenres()
}