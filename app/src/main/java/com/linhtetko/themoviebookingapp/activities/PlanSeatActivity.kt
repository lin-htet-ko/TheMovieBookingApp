package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.adapters.PlanSeatAdapter
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SeatVO
import com.linhtetko.themoviebookingapp.delegates.PlanSeatDelegate
import com.linhtetko.themoviebookingapp.util.showSnackBar
import com.linhtetko.themoviebookingapp.viewpods.SimpleAppbarViewPod
import kotlinx.android.synthetic.main.activity_plan_seat.*
import kotlinx.android.synthetic.main.view_pod_simple_appbar.view.*
import java.text.SimpleDateFormat
import java.util.*

class PlanSeatActivity : AppCompatActivity(R.layout.activity_plan_seat), PlanSeatDelegate {

    private val mMovieModel: MovieBookingModel = MovieBookingModelImpl
    private lateinit var planSeatAdapter: PlanSeatAdapter

    companion object {

        var selectSeats = listOf<SeatVO>()
        var seat: SeatVO? = null

        fun newIntent(context: Context): Intent = Intent(context, PlanSeatActivity::class.java)
    }

    var seats: MutableList<SeatVO> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionbar()
        setUpPlanSeats()
        setUpListener()
        setInitialDataForConfirm()


        getMovieData()
        getPlanSeats()
    }

    private fun setInitialDataForConfirm() {
        btnPsConfirm.text = getString(
            R.string.lbl_buy_ticket_for_s, "0"
        )
    }

    private fun getMovieData() {
        ChooseCinemaAndTimeActivity.cinema?.apply {
            tvPsCinema.text = cinema ?: ""
        }
        MovieDetailActivity.movieId?.let {
            mMovieModel.getDbMovie(it)?.apply {
                tvPsMovieName.text = title
            }
        }
        ChooseCinemaAndTimeActivity.dateTime?.apply {
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = sdf.parse(dateFormat)
            val outSdf = SimpleDateFormat("EEEE, dd MMMM,", Locale.getDefault())
            tvPsDateAndTime.text =
                "${outSdf.format(date)} ${ChooseCinemaAndTimeActivity.cinemaDateTimeSlot?.startTime ?: ""}"
        }
    }

    private fun getPlanSeats() {
        ChooseCinemaAndTimeActivity.dateTime?.let { dateTimeVO ->
            ChooseCinemaAndTimeActivity.cinemaDateTimeSlot?.cinemaDateTimeSlotId?.let { id ->
                mMovieModel.getMovieSeats(
                    cinemaDayTimeSlotId = id.toString(),
                    bookingDate = dateTimeVO.dateFormat,
                    onSuccess = {
                        val items = it.toMutableList().flatten()
                        seats.clear()
                        seats.addAll(items)
                        planSeatAdapter.setSeats(items)
                    },
                    onFailure = {
                        showErrorMessage(it)
                    }
                )
            }
        }
    }

    private fun showErrorMessage(message: String) {
        clPlantSeat.showSnackBar(message)
    }

    private fun setUpListener() {
        btnPsConfirm.setOnClickListener { startActivity(BuySnackActivity.newIntent(this)) }
    }

    private fun setUpActionbar() {
        setSupportActionBar((vpPsActionbar as SimpleAppbarViewPod).tbVpSa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpPlanSeats() {
        planSeatAdapter = PlanSeatAdapter(
            planSeatDelegate = this
        )
        rvPsSeats.layoutManager = GridLayoutManager(this, 14)
        rvPsSeats.adapter = planSeatAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onClickPlanSeat(seatId: Int, position: Int) {

        seat = seats[position]
        if (seat != null) {
            seats[position] = seat!!.copy(isSelected = !seat!!.isSelected)
        }

        val selectedSeats = seats.filter { it.isSelected }
        selectSeats = selectedSeats
        tvPsTicketNum.text =
            if (selectedSeats.isNotEmpty()) selectedSeats.size.toString() else "0"
        tvPsSeatsNum.text = selectedSeats.joinToString("/ ") { "${it.seatName}" }
        btnPsConfirm.text = getString(
            R.string.lbl_buy_ticket_for_s,
            if (selectedSeats.isNotEmpty()) selectedSeats.sumOf { it.price ?: 0 }.toString() else 0
        )
        planSeatAdapter.setSeats(seats)

    }
}