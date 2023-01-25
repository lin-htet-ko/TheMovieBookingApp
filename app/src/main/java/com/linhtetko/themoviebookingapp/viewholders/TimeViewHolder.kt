package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.TimeSlotVO
import com.linhtetko.themoviebookingapp.delegates.TimeClickDelegate
import kotlinx.android.synthetic.main.view_holder_time.view.*

class TimeViewHolder(itemView: View, private val delegate: TimeClickDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(cinemaPosition: Int, timeSlotVO: TimeSlotVO) {
        itemView.tvTime.text = timeSlotVO.startTime

        if (timeSlotVO.isSelected) {
            itemView.tvTime.setTextColor(itemView.resources.getColor(android.R.color.white))
            itemView.tvTime.background =
                AppCompatResources.getDrawable(
                    itemView.context,
                    R.drawable.background_btn_select_timeslot
                )
        } else {
            itemView.tvTime.setTextColor(itemView.resources.getColor(android.R.color.black))
            itemView.tvTime.background =
                AppCompatResources.getDrawable(itemView.context, R.drawable.background_btn_auth)
        }

        itemView.tvTime.setOnClickListener {
            timeSlotVO.cinemaDateTimeSlotId?.let { id ->
                delegate.onTimeClick(
                    cinemaPosition,
                    id
                )
            }
        }
    }
}