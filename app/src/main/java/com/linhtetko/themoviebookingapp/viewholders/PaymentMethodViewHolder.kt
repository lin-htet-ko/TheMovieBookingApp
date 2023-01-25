package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.PaymentMethodVO
import com.linhtetko.themoviebookingapp.delegates.PaymentMethodDelegate
import kotlinx.android.synthetic.main.view_holder_payment_method.view.*

class PaymentMethodViewHolder(
    itemView: View,
    private val paymentMethodDelegate: PaymentMethodDelegate
) : RecyclerView.ViewHolder(itemView) {

    fun bindData(paymentMethodVO: PaymentMethodVO) {

        paymentMethodVO.apply {
            itemView.apply {
                tvVhPmCardType.text = name
                tvVhPmCardCategory.text = description

                if (isSelect) {
                    tvVhPmCardType.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorPrimaryLightMode
                        )
                    )
                    tvVhPmCardCategory.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorPrimaryLightMode
                        )
                    )
                    ivVhPmCard.setImageResource(R.drawable.ic_baseline_credit_card_selected_24)
                } else {
                    tvVhPmCardType.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.black
                        )
                    )
                    tvVhPmCardCategory.setTextColor(
                        ContextCompat.getColor(
                            itemView.context,
                            R.color.colorGray
                        )
                    )
                    ivVhPmCard.setImageResource(R.drawable.ic_baseline_credit_card_24)
                }
            }
        }
        itemView.setOnClickListener {
            paymentMethodVO.id?.let { id ->
                paymentMethodDelegate.onSelectPayment(id)
            }
        }
    }
}