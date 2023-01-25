package com.linhtetko.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.AuthVO

@Dao
interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(authVO: AuthVO)

    @Query("SELECT * FROM profile")
    fun getProfile(): List<AuthVO>

    @Query("DELETE FROM profile")
    fun deleteProfile()
}