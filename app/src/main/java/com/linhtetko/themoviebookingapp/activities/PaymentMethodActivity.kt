package com.linhtetko.themoviebookingapp.activities

import alirezat775.lib.carouselview.Carousel
import alirezat775.lib.carouselview.CarouselView
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.adapters.PaymentCardAdapter
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CheckoutRequestVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CheckoutVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CreditCardVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SnackRequestVO
import com.linhtetko.themoviebookingapp.util.showSnackBar
import com.linhtetko.themoviebookingapp.viewpods.SimpleAppbarViewPod
import kotlinx.android.synthetic.main.activity_payment_method.*
import kotlinx.android.synthetic.main.view_pod_simple_appbar.view.*

class PaymentMethodActivity : AppCompatActivity(R.layout.activity_payment_method) {

    companion object {
        var creditCard: CreditCardVO? = null
        var checkoutVo: CheckoutVO? = null
        fun newIntent(context: Context) = Intent(context, PaymentMethodActivity::class.java)
    }

    private lateinit var paymentCardAdapter: PaymentCardAdapter
    private lateinit var carousel: Carousel
    private var mCards: List<CreditCardVO> = listOf()

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionbar()
        setUpPaymentCards()
        setUpListener()

        bindPrice()
        getToken()
    }

    private fun bindPrice() {
        tvPmAmount.text = "\$ ${BuySnackActivity.totalPrice}"
    }

    private fun getToken() {
        getProfile()
    }

    private fun getProfile() {
        mMovieBookingModel.getProfile(
            onSuccess = {
                it?.apply {
                    if (cards != null) {
                        mCards = cards
                        paymentCardAdapter.setNewData(cards)
                    }
                }
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun showErrorMessage(message: String) {
        clPM.showSnackBar(message)
    }

    private fun setUpPaymentCards() {
        paymentCardAdapter = PaymentCardAdapter()
        carousel = Carousel(this, cvPmCards, paymentCardAdapter)
        carousel.setOrientation(CarouselView.HORIZONTAL, false)
//        carousel.autoScroll(true, 5000, true)
        carousel.scaleView(true)
    }

    private fun setUpListener() {
        btnPmAddNewCard.setOnClickListener {
            startActivity(CardInfoActivity.newIntent(this))
        }

        btnPmConfirm.setOnClickListener {
            if (mCards.isNotEmpty()) {
                creditCard = mCards.getOrNull(carousel.getCurrentPosition())

                checkout()
            }
        }
    }

    private fun checkout() {
        val movieVo = MovieDetailActivity.movieId?.let { mMovieBookingModel.getDbMovie(it) }
        val dayTimeSlot = ChooseCinemaAndTimeActivity.cinemaDateTimeSlot
        val cinema = ChooseCinemaAndTimeActivity.cinema
        val seat = PlanSeatActivity.selectSeats.joinToString(",") { it.symbol ?: "" }
        val seats = PlanSeatActivity.selectSeats.joinToString(",") { it.seatName ?: "" }
        val bookingDate = ChooseCinemaAndTimeActivity.dateTime
        val paymentMethodVO = PaymentMethodActivity.creditCard
        val snacks = BuySnackActivity.selectedSnack
        val checkout = CheckoutRequestVO(
            bookingDate = bookingDate?.dateFormat ?: "",
            cardId = paymentMethodVO?.id ?: 0,
            cinemaDayTimeslotId = dayTimeSlot?.cinemaDateTimeSlotId ?: 0,
            movieId = movieVo?.id ?: 0,
            cinemaId = cinema?.cinemaId ?: 0,
            row = seat,
            seatNumber = seats,
            snacks = snacks.map { SnackRequestVO(it.id ?: 0, it.quantity ?: 0) },
        )

        mMovieBookingModel.postCheckout(
            checkout = checkout,
            onSuccess = {
                checkoutVo = it
                startActivity(VoucherActivity.newIntent(this))
            },
            onFailure = {
                showErrorMessage(it)
            })
    }

    override fun onResume() {
        super.onResume()
        mMovieBookingModel.getProfile(
            onSuccess = {
                mCards = it?.cards ?: listOf()
                if (mCards.isNotEmpty()) {
                    paymentCardAdapter.setNewData(mCards.reversed())
                }

            },
            onFailure = {
                showErrorMessage(it)
            }
        )

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    private fun setUpActionbar() {
        setSupportActionBar((vpPmActionBar as SimpleAppbarViewPod).tbVpSa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}