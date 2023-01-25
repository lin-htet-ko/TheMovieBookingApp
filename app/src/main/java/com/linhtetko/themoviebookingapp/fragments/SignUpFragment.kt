package com.linhtetko.themoviebookingapp.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.delegates.AuthDelegate
import com.linhtetko.themoviebookingapp.delegates.RegisterDelegate
import com.linhtetko.themoviebookingapp.viewpods.AuthMethodViewPod
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.view.*

class SignUpFragment : Fragment(), AuthDelegate {

    companion object {
        const val AUTH_TYPE = "register"
    }

    private lateinit var authMethod: AuthMethodViewPod
    private lateinit var mRegisterDelegate: RegisterDelegate

    override fun onAttach(context: Context) {
        mRegisterDelegate = context as RegisterDelegate
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAuthMethodViewPod(view)
    }

    private fun setUpAuthMethodViewPod(view: View) {
        authMethod = view.vpAuthSignUp as AuthMethodViewPod
        authMethod.setUpFbBtnText(getString(R.string.lbl_sign_up_with_facebook))
        authMethod.setUpGoogleBtnText(getString(R.string.lbl_sign_up_with_google))
        authMethod.setDelegate(this)

        authMethod.setConfirmClickListener()
    }

    private fun getField(fields: (name: String, email: String, phone: Long, password: String) -> Unit) {
        val name = tieSignUpName.text.toString()
        val email = tieSignUpEmail.text.toString()
        val phone = tieSignUpPhone.text.toString()
        val password = tieSignUpPassword.text.toString()

        if (name.isEmpty()) {
            tieSignUpName.error = getString(R.string.lbl_required)
            return
        }
        if (email.isEmpty()) {
            tieSignUpEmail.error = getString(R.string.lbl_required)
            return
        }
        if (password.isEmpty()) {
            tieSignUpPassword.error = getString(R.string.lbl_required)
            return
        }
        if (phone.isEmpty()) {
            tieSignUpPhone.error = getString(R.string.lbl_required)
            return
        }

        fields(name, email, phone.toLong(), password)

    }

    override fun onConfirmClick() {
        getField { name, email, phone, password ->
            mRegisterDelegate.onClickConfirm(name, email, phone, password)
        }
    }

}