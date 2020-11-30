package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.repository.repo.user.UserRepository
import hu.bme.aut.freelancerandroid.ui.user.login.LoginViewModel
import hu.bme.aut.freelancerandroid.ui.user.login.LoginViewModelProviderFactory
import hu.bme.aut.freelancerandroid.ui.user.login.SessionManager
import hu.bme.aut.freelancerandroid.util.GlobalVariable

class LoginActivity : AppCompatActivity(){

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val userRepository = UserRepository()
        val loginViewModelProviderFactory= LoginViewModelProviderFactory(userRepository)
        loginViewModel = ViewModelProvider(this, loginViewModelProviderFactory).get(LoginViewModel::class.java)
    }

//    override fun onStart() {
//        super.onStart()
//        val sessionManager = SessionManager(this)
//        var isUserLoggedIn = sessionManager.isLoggedIn()
//
//        if (isUserLoggedIn){
//            GlobalVariable.token = sessionManager.getToken()
//            GlobalVariable.activeUser = sessionManager.getId()
//
//            navController.navigate(R.id.action_loginFragment_to_home_screen)
//        }
//    }
}