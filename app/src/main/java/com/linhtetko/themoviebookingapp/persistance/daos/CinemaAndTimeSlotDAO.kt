package com.linhtetko.themoviebookingapp.persistance.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CinemaAndTimeSlotVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CinemaVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.TimeSlotVO

@Dao
interface CinemaAndTimeSlotDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCinemaAndTimeSlots(cinemas: CinemaAndTimeSlotVO)

    @Query("SELECT * FROM cinema_and_timeslots")
    fun getCinemaAndTimeSlotVO(): List<CinemaAndTimeSlotVO>

    @Query("SELECT * FROM cinema_and_timeslots WHERE date = :date")
    fun getCinemaByDate(date: String): CinemaAndTimeSlotVO?
}