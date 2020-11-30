package hu.bme.aut.freelancerandroid.ui.user.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.freelancerandroid.LoginActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.fragments.PackageScreenFragment
import hu.bme.aut.freelancerandroid.fragments.ProfileFragment
import hu.bme.aut.freelancerandroid.repository.model.LoginData
import hu.bme.aut.freelancerandroid.repository.repo.user.UserRepository
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.login.*
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep


class LoginFragment : Fragment(R.layout.login), View.OnClickListener{

    lateinit var navController: NavController
    lateinit var loginViewModel: LoginViewModel
    lateinit var sessionManager: SessionManager
    val TAG = "LoginFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        loginViewModel = (activity as LoginActivity).loginViewModel
        sessionManager = SessionManager(activity as LoginActivity)

        var isUserLoggedIn = sessionManager.isLoggedIn()

        if (isUserLoggedIn){
            GlobalVariable.token = sessionManager.getToken()
            GlobalVariable.activeUser = sessionManager.getId()
            navController.navigate(R.id.action_loginFragment_to_home_screen)
        }

        loginViewModel.activeUser.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
            is Resource.Success -> {
                hideProgressBar()
                response.data?.let { loginResponse ->
                    sessionManager.createLoginSession(loginResponse.token,loginResponse.currentUserId)
                    GlobalVariable.token = loginResponse.token
                    GlobalVariable.activeUser = loginResponse.currentUserId
                    navController.navigate(R.id.action_loginFragment_to_home_screen)
                }
            }
            is Resource.Error -> {
                hideProgressBar()
                response.message?.let { message ->
                    Snackbar.make(view, "Hibás felhasználónév vagy jelszó!", Snackbar.LENGTH_SHORT).show()
                    Log.e(TAG, "An error occured view: $message")
                }
            }
            is Resource.Loading -> {
                showProgressBar()

            }

        }
        })



        btnSignIn.setOnClickListener(this)
        btnForgotPassword.setOnClickListener(this)
        btnRegisterAcc.setOnClickListener(this)


    }

    private fun hideProgressBar() {
        paginationProgressBar.isGone=false
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
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
                    val email = etPersonName.text.toString()
                    val password = etPassword.text.toString()
                    //loginViewModel.loginUser("morvaiakos1998@gmail.com","luluka")
                     loginViewModel.loginUser(email,password)



                    //navController.navigate(R.id.action_loginFragment_to_home_screen)
                }
            }}
            R.id.btnForgotPassword -> {navController.navigate(R.id.action_loginFragment_to_forgotPasswordFragment)}
            R.id.btnRegisterAcc -> {navController.navigate(R.id.action_loginFragment_to_registerFormFragment)}
        }
    }

}