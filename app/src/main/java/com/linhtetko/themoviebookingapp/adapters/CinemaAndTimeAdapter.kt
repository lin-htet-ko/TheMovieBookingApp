package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.CinemaVO
import com.linhtetko.themoviebookingapp.delegates.TimeClickDelegate
import com.linhtetko.themoviebookingapp.viewholders.TimeAndCinemaViewHolder
import kotlinx.android.synthetic.main.view_holder_time_and_cinema.view.*

class CinemaAndTimeAdapter(
    private val timeClickDelegate: TimeClickDelegate
): RecyclerView.Adapter<TimeAndCinemaViewHolder>() {

    private var cinemas : List<CinemaVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeAndCinemaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_time_and_cinema, parent, false)
        return TimeAndCinemaViewHolder(view, timeClickDelegate)
    }

    override fun onBindViewHolder(holder: TimeAndCinemaViewHolder, position: Int) {
        if (cinemas.isNotEmpty()){
            holder.bindData(position,cinemas[position])
        }
    }

    override fun getItemCount(): Int = cinemas.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<CinemaVO>){
        cinemas = items
        notifyDataSetChanged()
    }
}