package com.linhtetko.themoviebookingapp.persistance.daos

import androidx.room.*
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.TokenVO

@Dao
interface TokenDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertToken(token: TokenVO)

    @Query("SELECT * FROM tokens ORDER BY id DESC LIMIT 1")
    fun getToken(): TokenVO?

    @Query("DELETE FROM tokens")
    fun deleteTokens()
}