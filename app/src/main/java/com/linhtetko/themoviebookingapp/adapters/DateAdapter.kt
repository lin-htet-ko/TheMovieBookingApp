package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.DateTimeVO
import com.linhtetko.themoviebookingapp.delegates.DateDelegate
import com.linhtetko.themoviebookingapp.viewholders.DateViewHolder

class DateAdapter(
    private val dateDelegate: DateDelegate
):RecyclerView.Adapter<DateViewHolder>() {

    private var dates : List<DateTimeVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_date, parent, false)
        return DateViewHolder(view, dateDelegate)
    }

    override fun onBindViewHolder(holder: DateViewHolder, position: Int) {
        if (dates.isNotEmpty()){
            holder.bindData(dates[position])
        }
    }

    override fun getItemCount(): Int = dates.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<DateTimeVO>){
        dates = items
        notifyDataSetChanged()
    }
}