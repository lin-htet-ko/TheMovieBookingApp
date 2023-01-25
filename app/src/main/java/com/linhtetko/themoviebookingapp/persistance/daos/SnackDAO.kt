package com.linhtetko.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SnackVO

@Dao
interface SnackDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSnacks(snacks: List<SnackVO>)

    @Query("SELECT * FROM snacks")
    fun getSnacks(): List<SnackVO>

    @Query("SELECT * FROM snacks WHERE id = :snackId")
    fun getSnack(snackId: Int): SnackVO?
}