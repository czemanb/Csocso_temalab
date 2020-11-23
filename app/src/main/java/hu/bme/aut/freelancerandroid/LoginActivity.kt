package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.freelancerandroid.repository.repo.user.UserRepository
import hu.bme.aut.freelancerandroid.ui.user.login.LoginViewModel
import hu.bme.aut.freelancerandroid.ui.user.login.LoginViewModelProviderFactory

class LoginActivity : AppCompatActivity(){

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val userRepository = UserRepository()
        val loginViewModelProviderFactory= LoginViewModelProviderFactory(userRepository)
        loginViewModel = ViewModelProvider(this, loginViewModelProviderFactory).get(LoginViewModel::class.java)
    }
}