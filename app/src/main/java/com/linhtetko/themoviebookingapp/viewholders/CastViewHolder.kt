package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.data.vos.moviedb.ActorVO
import com.linhtetko.themoviebookingapp.util.loadImageFromMDB
import kotlinx.android.synthetic.main.view_holder_cast.view.*

class CastViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindData(cast: ActorVO) {
        cast.profile_path?.let { itemView.ivVhCast.loadImageFromMDB(it) }
    }

}