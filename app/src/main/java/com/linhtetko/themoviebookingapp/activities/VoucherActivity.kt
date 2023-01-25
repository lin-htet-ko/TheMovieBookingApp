package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.activities.ChooseCinemaAndTimeActivity.Companion.cinema
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.*
import com.linhtetko.themoviebookingapp.data.vos.moviedb.MovieVO
import com.linhtetko.themoviebookingapp.util.loadImageFromMDB
import com.linhtetko.themoviebookingapp.util.showSnackBar
import com.linhtetko.themoviebookingapp.viewpods.SimpleAppbarViewPod
import kotlinx.android.synthetic.main.activity_vouvher.*
import kotlinx.android.synthetic.main.view_pod_simple_appbar.view.*

class VoucherActivity : AppCompatActivity(R.layout.activity_vouvher) {

    companion object {
        fun newIntent(context: Context) = Intent(context, VoucherActivity::class.java)
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionbar()

        getCheckoutData()
    }

    private fun getCheckoutData() {
        val movieVo = MovieDetailActivity.movieId?.let { mMovieBookingModel.getDbMovie(it) }
        val cinema = ChooseCinemaAndTimeActivity.cinema
        val bookingDate = ChooseCinemaAndTimeActivity.dateTime

        PaymentMethodActivity.checkoutVo?.let {
            if (movieVo != null) {
                if (cinema != null) {
                    bindData(it, movieVo, bookingDate, cinema)
                }
            }
        }
    }

    private fun bindData(
        checkoutVO: CheckoutVO?,
        movieVO: MovieVO,
        bookingDate: DateTimeVO?,
        cinemaVO: CinemaVO
    ) {
        checkoutVO?.apply {
            ivVcMovieImg.loadImageFromMDB(movieVO.posterPath ?: "")
            tvVcMovieName.text = movieVO.title ?: ""
            tvVcMovieDuration.text = movieVO.runtime.toString() ?: ""
            tvVcIm.text = movieVO.imdbId ?: ""

            tvVcBookingNo.text = bookingNo ?: ""
            tvVcShowingTime.text = "${timeslot?.startTime ?: ""} - ${bookingDate?.dateFormat ?: ""}"
            tvVcTheater.text = cinemaVO.cinema ?: ""
            tvVcRow.text = row ?: ""
            tvVcSeats.text = seat ?: ""
            tvVcPrice.text = "\$${total ?: ""}"

            val bitmap = BarcodeEncoder().encodeBitmap(bookingNo, BarcodeFormat.CODE_128, 512, 200)
            ivVcQrCode.setImageBitmap(bitmap)
        }
    }

    private fun showErrMessage(message: String) {
        clVoucher.showSnackBar(message)
    }

    private fun setUpActionbar() {
        setSupportActionBar((vpVcAppbar as SimpleAppbarViewPod).tbVpSa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_close_24)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finishAffinity()
            startActivity(MainActivity.newIntent(this))
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}