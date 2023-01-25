package com.linhtetko.themoviebookingapp.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.linhtetko.themoviebookingapp.R
import com.linhtetko.themoviebookingapp.delegates.AuthDelegate
import com.linhtetko.themoviebookingapp.delegates.LoginDelegate
import com.linhtetko.themoviebookingapp.viewpods.AuthMethodViewPod
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment : Fragment(), AuthDelegate {


    companion object {
        const val AUTH_TYPE = "Login"
    }

    private lateinit var mLoginDelegate: LoginDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mLoginDelegate = context as LoginDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpAuthMethodViewPod(view)
    }

    private fun setUpAuthMethodViewPod(view: View) {
        val authMethod = view.vpAuthLogin as AuthMethodViewPod
        authMethod.setUpFbBtnText(getString(R.string.lbl_sign_up_with_facebook))
        authMethod.setUpGoogleBtnText(getString(R.string.lbl_sign_up_with_google))
        authMethod.setDelegate(this)

        authMethod.setConfirmClickListener()
    }

    private fun getFields(fields: (email: String, password: String) -> Unit) {

        val email = tieLoginEmail.text.toString().trim()
        val password = tieLoginPassword.text.toString().trim()

//        //todo replace with variable email, password intend of Hard Code
//        val email = "linhtetko1518@gmail.com"
//        val password = "LinHtetKo123"

        if (email.isEmpty()) {
            tieLoginEmail.error = getString(R.string.lbl_required)
            return
        }
        if (password.isEmpty()) {
            tieLoginPassword.error = getString(R.string.lbl_required)
            return
        }

        fields(email, password)
    }

    override fun onConfirmClick() {
        getFields { email, password ->
            mLoginDelegate.onClickConfirm(email, password)
        }

    }
}