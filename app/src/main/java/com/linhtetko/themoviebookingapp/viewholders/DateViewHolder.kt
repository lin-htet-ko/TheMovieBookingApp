package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.DateTimeVO
import com.linhtetko.themoviebookingapp.delegates.DateDelegate
import kotlinx.android.synthetic.main.view_holder_date.view.*

class DateViewHolder(itemView: View, private val dateDelegate: DateDelegate) : RecyclerView.ViewHolder(itemView) {

    fun bindData(dateTimeVO: DateTimeVO) {
        dateTimeVO.apply {
            itemView.tvVhDateText.text = dateText.take(3)
            itemView.tvVhDate.text = date.toString()

            if (isSelect){
                itemView.tvVhDateText.setTextColor(itemView.resources.getColor(android.R.color.white))
                itemView.tvVhDate.setTextColor(itemView.resources.getColor(android.R.color.white))
            }else{
                itemView.tvVhDateText.setTextColor(itemView.resources.getColor(android.R.color.darker_gray))
                itemView.tvVhDate.setTextColor(itemView.resources.getColor(android.R.color.darker_gray))
            }

            itemView.setOnClickListener {
                dateDelegate.onDateViewClick(dateTimeVO.id)
            }
        }
    }
}