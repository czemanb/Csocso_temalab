package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.R
import kotlinx.android.synthetic.main.login.*

class LoginFragment : Fragment(R.layout.login), View.OnClickListener{

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        btnSignIn.setOnClickListener(this)
        btnForgotPassword.setOnClickListener(this)
        btnRegisterAcc.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnSignIn -> {navController.navigate(R.id.action_loginFragment_to_mainFragment)}
            R.id.btnForgotPassword -> {navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment)}
            R.id.btnRegisterAcc -> {navController.navigate(R.id.action_loginFragment_to_registerFormFragment)}
        }
    }
}