package com.linhtetko.themoviebookingapp.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.adapters.ComboSnackAdapter
import com.linhtetko.themoviebookingapp.adapters.PaymentMethodAdapter
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModel
import com.linhtetko.themoviebookingapp.data.model.MovieBookingModelImpl
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.PaymentMethodVO
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SnackVO
import com.linhtetko.themoviebookingapp.delegates.PaymentMethodDelegate
import com.linhtetko.themoviebookingapp.delegates.SnackDelegate
import com.linhtetko.themoviebookingapp.util.showSnackBar
import com.linhtetko.themoviebookingapp.viewpods.SimpleAppbarViewPod
import kotlinx.android.synthetic.main.activity_buy_snack.*
import kotlinx.android.synthetic.main.view_pod_simple_appbar.view.*

class BuySnackActivity : AppCompatActivity(R.layout.activity_buy_snack), SnackDelegate,
    PaymentMethodDelegate {

    companion object {
        var totalPrice = 0
        val selectedSnack = mutableListOf<SnackVO>()
        fun newIntent(context: Context) = Intent(context, BuySnackActivity::class.java)
    }

    private var price = 0

    private val mMovieBookingModel: MovieBookingModel = MovieBookingModelImpl
    private lateinit var snackAdapter: ComboSnackAdapter
    private lateinit var paymentMethodAdapter: PaymentMethodAdapter
    private var snacks = listOf<SnackVO>()
    private var paymentMethods = listOf<PaymentMethodVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setUpActionBar()
        setUpComboSnackItems()
        sepPaymentMethods()
        setUpListener()

        bindSubTotalPrice(0)
        setGetPrice()
        getToken()
    }

    private fun getToken() {
        MovieBookingModelImpl.getToken { token ->
            if (token != null) {
                getSnackList(token)
                getPaymentMethods(token)
            }
        }
    }

    private fun getPaymentMethods(token: String) {
        mMovieBookingModel.getPaymentMethods(
            onSuccess = {
                paymentMethods = it
                paymentMethodAdapter.setNewData(it)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun setGetPrice() {
        PlanSeatActivity.selectSeats.forEach { seatVO ->
            seatVO.apply {
                bindPrice(price ?: 0, "add")
            }
        }
    }

    private fun getSnackList(token: String) {
        mMovieBookingModel.getSnackList(
            onSuccess = {
                refreshSnack(it)
            },
            onFailure = {
                showErrorMessage(it)
            }
        )
    }

    private fun bindPrice(total: Int, action: String) {
        if (action == "add")
            totalPrice += total
        else if (action == "sub")
            totalPrice -= total
        btnButSnackPay.text = getString(R.string.lbl_pay_d, totalPrice)
    }

    private fun bindSubTotalPrice(price: Int) {
        tvBuySnackSubTotal.text = getString(R.string.lbl_sub_total_s, price.toString())
    }

    private fun showErrorMessage(message: String) {
        clBS.showSnackBar(message)
    }

    private fun setUpListener() {
        btnButSnackPay.setOnClickListener {
            startActivity(PaymentMethodActivity.newIntent(this))
        }
    }

    private fun sepPaymentMethods() {
        paymentMethodAdapter = PaymentMethodAdapter(paymentMethodDelegate = this)
        rvBuySnackPaymentMethods.adapter = paymentMethodAdapter
    }

    private fun setUpComboSnackItems() {
        snackAdapter = ComboSnackAdapter(snackDelegate = this)
        rvComboItems.adapter = snackAdapter
    }

    private fun setUpActionBar() {
        setSupportActionBar((vpBuySnackAb as SimpleAppbarViewPod).tbVpSa)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun refreshSnack(items: List<SnackVO>) {
        snacks = items
        snackAdapter.setNewData(snacks)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) super.onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    override fun onAddSnack(snackId: Int) {
        val snack = mMovieBookingModel.getDbSnack(snackId)
        snack?.let { snackVO ->
            selectedSnack.add(snackVO.copy(quantity = (snackVO.quantity ?: 0) + 1))
            price += (snackVO.price ?: 0)
            bindPrice((snackVO.price ?: 0), "add")
            bindSubTotalPrice(price)
            snacks = snacks.map {
                if (it.id == snackVO.id) it.copy(
                    quantity = (snackVO.quantity ?: 0) + 1
                ) else it
            }
            refreshSnack(snacks)
        }
    }

    override fun onReduceSnack(snackId: Int) {
        val snackItem = mMovieBookingModel.getDbSnack(snackId)
        snackItem?.let { snackVO ->
            if (snacks.isNotEmpty()) {
                val tempSnacks = selectedSnack
                for (snack in tempSnacks) {
                    if (snack.id == snackVO.id) {
                        selectedSnack.removeLast()
                        break
                    }
                }
            }

            price -= (snackVO.price ?: 0)
            bindPrice((snackVO.price ?: 0), "sub")
            bindSubTotalPrice(price)
            refreshSnack(snacks.map {
                if (it.id == snackVO.id) it.copy(
                    quantity = (snackVO.quantity ?: 0) - 1
                ) else it
            })
        }
    }

    override fun onSelectPayment(paymentMethodId: Int) {
        val paymentMethodVO = mMovieBookingModel.getDbPaymentMethod(paymentMethodId)
        val temp = paymentMethods.map {
            if (it.id == paymentMethodVO?.id) {
                it.isSelect = true
                it
            } else {
                it
            }
        }
        paymentMethodAdapter.setNewData(temp)
    }
}