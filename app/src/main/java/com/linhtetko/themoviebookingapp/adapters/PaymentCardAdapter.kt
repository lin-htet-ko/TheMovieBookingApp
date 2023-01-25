package com.linhtetko.themoviebookingapp.adapters

import alirezat775.lib.carouselview.CarouselAdapter
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CreditCardVO
import kotlinx.android.synthetic.main.view_holder_payment_card.view.*

class PaymentCardAdapter(
    private var cards: List<CreditCardVO> = listOf()
) : CarouselAdapter() {

    override fun onBindViewHolder(holder: CarouselAdapter.CarouselViewHolder, position: Int) {
        val viewHolder = holder as PaymentCardViewHolder

        if (cards.isNotEmpty()) {
            viewHolder.bindData(cards[position])
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentCardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_payment_card, parent, false)
        return PaymentCardViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<CreditCardVO>) {
        cards = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return cards.size
    }
    inner class PaymentCardViewHolder(itemView: View) :
        CarouselAdapter.CarouselViewHolder(itemView) {

        fun bindData(creditCardVO: CreditCardVO) {
            creditCardVO.apply {
                itemView.apply {
                    tvVhPcNumber.text =
                        context.getString(R.string.lbl_payment_card, (cardNumber ?: "0000").takeLast(3))
                    tvVhPcHolder.text = cardHolder ?: ""
                    tvVhPcExpire.text = expirationDate ?: ""
                }
            }
        }
    }

}