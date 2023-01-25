package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SEAT_AVAILABLE
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SEAT_TEXT
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SeatVO
import com.linhtetko.themoviebookingapp.delegates.PlanSeatDelegate
import kotlinx.android.synthetic.main.view_holder_seat.view.*
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SEAT_RESERVED as SEAT_RESERVED

class SeatViewHolder(itemView: View, private val planSeatDelegate: PlanSeatDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(item: SeatVO, position: Int) {
        when(item.type) {
            SEAT_AVAILABLE -> {

                itemView.setOnClickListener {
                    item.id?.let { id -> planSeatDelegate.onClickPlanSeat(id, position) }
                }

                itemView.tvVhSeat.visibility = View.GONE
                itemView.flVhSeat.background = ContextCompat.getDrawable(
                    itemView.context,
                    R.drawable.background_available_seat
                )
            }
            SEAT_TEXT -> {
                itemView.tvVhSeat.visibility = View.VISIBLE
                itemView.tvVhSeat.setTextColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.black
                    )
                )
                itemView.tvVhSeat.text = item.symbol
                itemView.flVhSeat.setBackgroundColor(
                    ContextCompat.getColor(
                        itemView.context,
                        R.color.white
                    )
                )
            }
            SEAT_RESERVED -> {
                itemView.tvVhSeat.visibility = View.GONE
                itemView.flVhSeat.background =
                    ContextCompat.getDrawable(itemView.context, R.drawable.background_reserved_seat)
            }
            else -> {
                itemView.tvVhSeat.visibility = View.GONE
                itemView.flVhSeat.background =
                    ContextCompat.getDrawable(itemView.context, android.R.color.transparent)
            }
        }

        if (item.isSelected) {
            itemView.setOnClickListener {
                item.id?.let { id -> planSeatDelegate.onClickPlanSeat(id, position) }
            }
            itemView.tvVhSeat.visibility = View.VISIBLE
            itemView.tvVhSeat.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.white
                )
            )
            itemView.tvVhSeat.text = (item.seatName?.removeRange(0..1) ?: "").toString()
            itemView.flVhSeat.background = ContextCompat.getDrawable(
                itemView.context,
                R.drawable.background_selected_seat
            )

        }
    }
}