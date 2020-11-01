package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.R
import kotlinx.android.synthetic.main.register_form.*

class RegisterFormFragment : Fragment(R.layout.register_form), View.OnClickListener {
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnRegister.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRegister -> {navController.navigate(R.id.action_registerFormFragment_to_home_screen)}
            R.id.btnCancel -> requireActivity().onBackPressed()

        }
    }
}