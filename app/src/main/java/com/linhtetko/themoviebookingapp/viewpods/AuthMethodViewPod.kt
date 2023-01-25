package com.linhtetko.themoviebookingapp.viewpods

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.linhtetko.themoviebookingapp.delegates.AuthDelegate
import com.linhtetko.themoviebookingapp.delegates.RegisterDelegate
import kotlinx.android.synthetic.main.view_pod_auth_method.view.*

class AuthMethodViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayoutCompat(context, attrs) {

    var mAuthDelegate: AuthDelegate? = null

    fun setUpFbBtnText(text: String) {
        tvAuthFbLabel.text = text
    }

    fun setUpGoogleBtnText(text: String) {
        tvAuthGoogleLabel.text = text
    }

    fun setDelegate(authDelegate: AuthDelegate){
        mAuthDelegate = authDelegate
    }

    fun setConfirmClickListener() {
        btnAuthConfirm.setOnClickListener {
            mAuthDelegate?.onConfirmClick()
        }
    }

}