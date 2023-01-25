package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SnackVO
import com.linhtetko.themoviebookingapp.delegates.SnackDelegate
import com.linhtetko.themoviebookingapp.viewholders.ComboSnackViewHolder

class ComboSnackAdapter(
    private var snacks: List<SnackVO> = listOf(),
    private val snackDelegate: SnackDelegate
): RecyclerView.Adapter<ComboSnackViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComboSnackViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_combo_snack, parent, false)
        return ComboSnackViewHolder(view, snackDelegate)
    }

    override fun onBindViewHolder(holder: ComboSnackViewHolder, position: Int) {
        if (snacks.isNotEmpty()){
            holder.bindData(snacks[position])
        }
    }

    override fun getItemCount(): Int = snacks.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(items: List<SnackVO>){
        snacks = items
        notifyDataSetChanged()
    }
}