package com.linhtetko.themoviebookingapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.data.vos.moviedb.ActorVO
import com.linhtetko.themoviebookingapp.viewholders.CastViewHolder

class CastAdapter(
) : RecyclerView.Adapter<CastViewHolder>() {

    private var casts : List<ActorVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_holder_cast, parent, false)
        return CastViewHolder(view)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        if (casts.isNotEmpty()){
            holder.bindData(casts[position])
        }
    }

    override fun getItemCount(): Int = casts.size

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(casts: List<ActorVO>) {
        this.casts = casts
        notifyDataSetChanged()
    }
}