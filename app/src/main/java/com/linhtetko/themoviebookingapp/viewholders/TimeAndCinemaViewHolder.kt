package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.adapters.TimeAdapter
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CinemaVO
import com.linhtetko.themoviebookingapp.delegates.TimeClickDelegate
import kotlinx.android.synthetic.main.view_holder_time_and_cinema.view.*

class TimeAndCinemaViewHolder(itemView: View, private val timeClickDelegate: TimeClickDelegate) : RecyclerView.ViewHolder(itemView) {

    fun bindData(position: Int, cinemaVO: CinemaVO) {
        cinemaVO.apply {
            itemView.tvVhTaCTitle.text = cinema ?: ""
            itemView.rvTaCTimes.adapter = TimeAdapter(position, timeClickDelegate, timeslots ?: listOf())
        }
    }
}