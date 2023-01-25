package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CreditCardVO
import com.linhtetko.themoviebookingapp.util.showSnackBar
import com.linhtetko.themoviebookingapp.viewpods.SimpleAppbarViewPod
import kotlinx.android.synthetic.main.activity_card_info.*
import kotlinx.android.synthetic.main.view_pod_simple_appbar.view.*

class CardInfoActivity : AppCompatActivity(R.layout.activity_card_info) {

    companion object {
        var cards: List<CreditCardVO> = listOf()
        const val IE_CARD_INFO = "card_info"
        fun newIntent(context: Context) = Intent(context, CardInfoActivity::class.java)
    }

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionbar()
        setUpListener()
    }

    private fun setUpListener() {
        btnCiPmConfirm.setOnClickListener {
            getToken()
        }
    }

    private fun getToken() {
        MovieBookingModelImpl.getToken { token ->
            if (token != null) {
                postCreditCard(token)
            }
        }
    }

    private fun postCreditCard(token: String) {
        val cardNumber = tieCiCardNumber.text.toString().trim()
        val cardHolder = tilCiCardHolder.text.toString().trim()
        val expireDate = tieCiExpireDate.text.toString().trim()
        val cvc = tilCiCVC.text.toString().trim()

        if (cardNumber.isEmpty()) {
            tieCiCardNumber.error = getString(R.string.lbl_required)
            return
        }
        if (cardHolder.isEmpty()) {
            tilCiCardHolder.error = getString(R.string.lbl_required)
            return
        }
        if (expireDate.isEmpty()) {
            tieCiExpireDate.error = getString(R.string.lbl_required)
            return
        }
        if (cvc.isEmpty()) {
            tilCiCVC.error = getString(R.string.lbl_required)
            return
        }

        mMovieBookingModel.postCreditCard(
            cardNumber = cardNumber.toInt(),
            cardHolder = cardHolder,
            expirationDate = expireDate,
            cvc = cvc.toInt(),
            onSuccess = {
                cards = it
                back(cards)
            },
            onFailure = {
                showErrMessage(it)
            }
        )

    }

    private fun back(items: List<CreditCardVO>) {
//        val intent = Intent()
//        intent.putExtra(IE_CARD_INFO, Gson().toJson(items))
//        setResult(RESULT_OK, intent)
        cards = items
        super.onBackPressed()
    }

    private fun showErrMessage(message: String) {
        clCI.showSnackBar(message)
    }

    private fun setUpActionbar() {
        setSupportActionBar((vpCiAppbar as SimpleAppbarViewPod).tbVpSa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            back(cards)
        }
        return super.onOptionsItemSelected(item)
    }
}