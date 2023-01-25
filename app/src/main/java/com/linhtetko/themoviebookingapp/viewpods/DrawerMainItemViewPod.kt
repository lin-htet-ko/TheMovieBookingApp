package com.linhtetko.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.widget.LinearLayoutCompat
import com.linhtetko.themoviebookingapp.delegates.DrawerMenuDelegate
import kotlinx.android.synthetic.main.view_pod_drawer_menu_item.view.*

class DrawerMainItemViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    fun setItem(@DrawableRes icon: Int, @StringRes title: Int, action: (() -> Unit)? = null) {
        ivVpDmiIcon.setImageResource(icon)
        tvVpDmiText.text = context.resources.getString(title)
        setOnClickListener {
            action?.invoke()
        }
    }
}