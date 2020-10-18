package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.R
import kotlinx.android.synthetic.main.forgot_password.*

class ForgotPasswordFragment : Fragment(R.layout.forgot_password),View.OnClickListener{

    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController= Navigation.findNavController(view)
        btnSend.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btnSend -> requireActivity().onBackPressed()
            R.id.btnCancel -> requireActivity().onBackPressed()

        }
    }
}