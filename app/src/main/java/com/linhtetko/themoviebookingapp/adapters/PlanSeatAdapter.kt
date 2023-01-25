package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SeatVO
import com.linhtetko.themoviebookingapp.delegates.PlanSeatDelegate
import com.linhtetko.themoviebookingapp.viewholders.SeatViewHolder

class PlanSeatAdapter(
    private var seats: List<SeatVO> = listOf(),
    private val planSeatDelegate: PlanSeatDelegate
): RecyclerView.Adapter<SeatViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_seat, parent, false)
        return SeatViewHolder(view, planSeatDelegate)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        if(seats.isNotEmpty()){
            holder.bindData(seats[position], position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setSeats(items: List<SeatVO>){
        seats = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = seats.size
}