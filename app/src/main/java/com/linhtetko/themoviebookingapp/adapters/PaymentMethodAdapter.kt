package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.PaymentMethodVO
import com.linhtetko.themoviebookingapp.delegates.PaymentMethodDelegate
import com.linhtetko.themoviebookingapp.viewholders.PaymentMethodViewHolder

class PaymentMethodAdapter(
    private var paymentMethods: List<PaymentMethodVO> = listOf(),
    private val paymentMethodDelegate: PaymentMethodDelegate
): RecyclerView.Adapter<PaymentMethodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentMethodViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_payment_method, parent, false)
        return PaymentMethodViewHolder(view, paymentMethodDelegate)
    }

    override fun onBindViewHolder(holder: PaymentMethodViewHolder, position: Int) {
        if (paymentMethods.isNotEmpty()){
            holder.bindData(paymentMethods[position])
        }
    }

    override fun getItemCount(): Int = paymentMethods.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<PaymentMethodVO>){
        paymentMethods = items
        notifyDataSetChanged()
    }
}