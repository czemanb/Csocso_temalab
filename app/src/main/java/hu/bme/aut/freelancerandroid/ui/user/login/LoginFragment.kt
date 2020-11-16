package hu.bme.aut.freelancerandroid.ui.user.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.R
import kotlinx.android.synthetic.main.login.*


class LoginFragment : Fragment(R.layout.login), View.OnClickListener{

    lateinit var navController: NavController
    lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        //loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        btnSignIn.setOnClickListener(this)
        btnForgotPassword.setOnClickListener(this)
        btnRegisterAcc.setOnClickListener(this)


    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnSignIn -> {when {
                etPersonName.text.toString().isEmpty() -> {
                    etPersonName.requestFocus()
                    etPersonName.error = "Please enter your email address"
                }
                etPassword.text.toString().isEmpty() -> {
                    etPassword.requestFocus()
                    etPassword.error = "Please enter your password"
                }
                else -> {
                    //loginViewModel.email = etPersonName.text.toString()
                    //loginViewModel.password = etPersonName.text.toString()
                    //loginViewModel.loginUser(loginViewModel.email,loginViewModel.password)
                    navController.navigate(R.id.action_loginFragment_to_home_screen)
                }
            }}
            R.id.btnForgotPassword -> {navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment)}
            R.id.btnRegisterAcc -> {navController.navigate(R.id.action_loginFragment_to_registerFormFragment)}
        }
    }

}