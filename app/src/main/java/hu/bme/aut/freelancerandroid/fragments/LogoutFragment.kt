package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.LoginActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.ui.user.login.SessionManager
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.logout_fragment.*

class LogoutFragment: Fragment(R.layout.logout_fragment){

    private lateinit var navConroller: NavController
    private lateinit var sessionManager: SessionManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        navConroller = Navigation.findNavController(view)
        sessionManager = SessionManager(activity as ApplicationActivity)

        btnYes.setOnClickListener {
            sessionManager.logoutUser()
            navConroller.navigate(R.id.action_logoutFragment_to_loginFragment2)
        }

        btnNo.setOnClickListener {
            navConroller.navigate(R.id.action_logoutFragment_to_homeFragment)
        }


    }
}
