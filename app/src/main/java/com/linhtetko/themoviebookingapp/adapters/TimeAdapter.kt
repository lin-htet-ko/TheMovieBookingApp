package com.linhtetko.themoviebookingapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.TimeSlotVO
import com.linhtetko.themoviebookingapp.delegates.TimeClickDelegate
import com.linhtetko.themoviebookingapp.viewholders.TimeViewHolder

class TimeAdapter(
    private val cinemaPosition: Int,
    private val timeClickDelegate: TimeClickDelegate,
    private val timeslots: List<TimeSlotVO>
): RecyclerView.Adapter<TimeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_time, parent, false)
        return TimeViewHolder(view, timeClickDelegate)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        if (timeslots.isNotEmpty()){
            holder.bindData(cinemaPosition, timeslots[position])
        }
    }

    override fun getItemCount(): Int = timeslots.size
}