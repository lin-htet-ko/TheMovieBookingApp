package com.linhtetko.themoviebookingapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.linhtetko.themoviebookingapp.data.vos.moviebookingapp.SnackVO
import com.linhtetko.themoviebookingapp.delegates.SnackDelegate
import kotlinx.android.synthetic.main.view_holder_combo_snack.view.*

class ComboSnackViewHolder(itemView: View, private val delegate: SnackDelegate) :
    RecyclerView.ViewHolder(itemView) {

    fun bindData(snackVO: SnackVO) {
        snackVO.apply {
            itemView.apply {
                tvComboSnackSize.text = name ?: ""
                tvComboSnackDetail.text = description ?: ""
                tvComboSnackPrice.text = "\$$price"
                btnComboSnackSubSign.isEnabled = (quantity ?: 0) > 0

                tvComboSnackCount.text = (quantity ?: 0).toString()
            }
        }

        itemView.btnComboSnackSubSign.setOnClickListener {
            snackVO.id?.let { id -> delegate.onReduceSnack(id) }
        }
        itemView.btnComboSnackPlusSign.setOnClickListener {
            snackVO.id?.let { id -> delegate.onAddSnack(id) }
        }
    }
}