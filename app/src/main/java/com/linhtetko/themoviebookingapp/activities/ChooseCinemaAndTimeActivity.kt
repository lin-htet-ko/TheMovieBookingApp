package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.adapters.CinemaAndTimeAdapter
import com.linhtetko.themoviebookingapp.adapters.DateAdapter
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CinemaVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.DateTimeVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.TimeSlotVO
import com.linhtetko.themoviebookingapp.delegates.DateDelegate
import com.linhtetko.themoviebookingapp.delegates.TimeClickDelegate
import com.linhtetko.themoviebookingapp.util.showSnackBar
import kotlinx.android.synthetic.main.activity_choose_cinema_and_time.*
import java.text.SimpleDateFormat
import java.util.*

class ChooseCinemaAndTimeActivity : AppCompatActivity(R.layout.activity_choose_cinema_and_time),
    TimeClickDelegate, DateDelegate {

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    companion object {

        var dateTime: DateTimeVO? = null
        var cinema: CinemaVO? = null
        var cinemaDateTimeSlot: TimeSlotVO? = null

        fun newIntent(context: Context): Intent =
            Intent(context, ChooseCinemaAndTimeActivity::class.java)
    }

    private var dates = mutableListOf<DateTimeVO>()
    private var cinemas = mutableListOf<CinemaVO>()

    private lateinit var dateAdapter: DateAdapter
    private lateinit var cinemaAndTimeAdapter: CinemaAndTimeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpDates()
        setUpCinemaAndTime()
        setUpListener()

        set14Days()
    }

    private fun set14Days() {
        val days = getNext14Days()
        dateAdapter.setNewData(days)

        val firstDateTime = days.first()
        dateTime = firstDateTime
        getTimeSlots(firstDateTime)
    }

    private fun setUpListener() {
        btnCcAtNext.setOnClickListener { startActivity(PlanSeatActivity.newIntent(this)) }
        btnCcAtBack.setOnClickListener { super.onBackPressed() }
    }

    private fun setUpCinemaAndTime() {
        cinemaAndTimeAdapter = CinemaAndTimeAdapter(this)
        rvCcAtCinemaAndTimes.adapter = cinemaAndTimeAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setUpDates() {
        dateAdapter = DateAdapter(this)
        rvCcAtDates.adapter = dateAdapter
    }

    override fun onTimeClick(cinemaPosition: Int, timeSlotId: Int) {

        cinemaDateTimeSlot = mMovieBookingModel.getDbTimeSlot(date = dateTime?.dateFormat ?: "", cinemaPosition, timeSlotId)

        cinemas = cinemas.map { cinemaVO ->
            cinemaVO.copy(
                timeslots = cinemaVO.timeslots?.map {
                    val state = cinemaDateTimeSlot?.cinemaDateTimeSlotId == it.cinemaDateTimeSlotId
                    if (state)
                        cinema = cinemaVO
                    it.copy(isSelected = state)
                }
            )
        }.toMutableList()

        cinemaAndTimeAdapter.setNewData(cinemas)
    }

    private fun showErrorMessage(message: String) {
        clCCAT.showSnackBar(message)
    }

    private fun getNext14Days(): List<DateTimeVO> {
        for (count in 0 until 14) {
            val currentDate = Calendar.getInstance()
            currentDate.add(Calendar.DATE, count)
            dates.add(
                DateTimeVO(
                    date = currentDate.get(Calendar.DAY_OF_MONTH),
                    dateText = SimpleDateFormat(
                        "EEEE",
                        Locale.getDefault()
                    ).format(currentDate.time),
                    dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                        currentDate.time
                    ),
                    isSelect = count == 0
                )
            )
        }
        return dates
    }

    override fun onDateViewClick(dateTimeId: Long) {

        dateTime = dates.firstOrNull { it.id == dateTimeId }
        dateTime?.let { getTimeSlots(it) }

        dates = dates.map {
            it.copy(
                isSelect = it.date == dateTime?.date
            )
        }.toMutableList()

        dateAdapter.setNewData(dates)
    }

    private fun getTimeSlots(dateTimeVO: DateTimeVO) {
        MovieDetailActivity.movieId?.let { id ->
            mMovieBookingModel.getDbMovie(id)?.let { movieVO ->
                mMovieBookingModel.getTimeSlots(
                    movieId = movieVO.id.toString(),
                    date = dateTimeVO.dateFormat,
                    onSuccess = {
                        cinemas = it.toMutableList()
                        if (cinemas.isNotEmpty()) {
                            cinemaDateTimeSlot = cinemas.getOrNull(0)?.timeslots?.getOrNull(0)
                                ?.copy(isSelected = true)
                            cinema = cinemas[0]

                            cinemas[0] = cinema!!
                        }
                        cinemaAndTimeAdapter.setNewData(cinemas)
                    },
                    onFailure = {
                        showErrorMessage(it)
                    }
                )
            }
        }
    }
}