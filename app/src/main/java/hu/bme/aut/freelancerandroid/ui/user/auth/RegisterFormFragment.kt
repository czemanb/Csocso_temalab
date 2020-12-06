package hu.bme.aut.freelancerandroid.ui.user.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.freelancerandroid.LoginActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.RegisterData
import hu.bme.aut.freelancerandroid.ui.user.LoginViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.register_form.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class RegisterFormFragment : Fragment(R.layout.register_form), View.OnClickListener {
    private lateinit var navController: NavController
    private lateinit var loginViewModel: LoginViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnRegister.setOnClickListener(this)
        btnCancel.setOnClickListener(this)
        loginViewModel = (activity as LoginActivity).loginViewModel
        loginViewModel.newUser.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                        navController.navigate(R.id.action_registerFormFragment_to_loginFragment)
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Snackbar.make(view, "Ilyen email címmel már regisztráltak!", Snackbar.LENGTH_SHORT).show()
                        Log.e("Register form", "An error occured view: $message")
                    }
                }
                is Resource.Loading -> {
                }

            }
        })
    }


    private fun isValidEmail(string: String): Boolean{
        val regex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"
        val pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE)
        val matcher: Matcher = pattern.matcher(string as CharSequence)
        return matcher.matches()
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRegister -> {
                when {
                    editTextPersonName.text.toString().isEmpty() -> {
                        editTextPersonName.requestFocus()
                        editTextPersonName.error = "Please enter your name!"
                    }
                    editTextPersonName.text.toString().length < 3 -> {
                        editTextPersonName.requestFocus()
                        editTextPersonName.error = "Too short name"
                    }
                    editTextEmailAddress.text.toString().isEmpty() -> {
                        editTextEmailAddress.requestFocus()
                        editTextEmailAddress.error = "Please enter your email address!"
                    }
//                    isValidEmail(editTextEmailAddress.text.toString())->{ //Todo
//                        editTextEmailAddress.requestFocus()
//                        editTextEmailAddress.error = "Not valid email!"
//                    }
                    editTextPhone.text.toString().isEmpty() -> {
                        editTextPhone.requestFocus()
                        editTextPhone.error = "Please enter your phone number!"
                    }
                    editTextPassword.text.toString().isEmpty() -> {
                        editTextPassword.requestFocus()
                        editTextPassword.error = "Please enter your password!"
                    }
                    editTextPassword.text.toString().length < 3 -> {
                        editTextPersonName.requestFocus()
                        editTextPersonName.error = "Min 5 character"
                    }

                    editTextPassword2.text.toString().isEmpty() -> {
                        editTextPassword2.requestFocus()
                        editTextPassword2.error = "Please enter your password again!"
                    }
                    editTextPassword.text.toString() != editTextPassword2.text.toString() ->{
                        editTextPassword.requestFocus()
                        editTextPassword2.error = "Please enter your password again! (pw1!=pw2)"
                    }

                    else -> {
                        val name = editTextPersonName.text.toString()
                        val email = editTextEmailAddress.text.toString()
                        val phone = editTextPhone.text.toString()
                        val password1 = editTextPassword.text.toString()
                        val password2= editTextPassword2.text.toString()
                        val hasInsurance = cbInsurance.isChecked
                        val newUser = RegisterData(name, email, phone, password1,password2,hasInsurance)
                        loginViewModel.addUser(newUser)
                        //navController.navigate(R.id.action_registerFormFragment_to_loginFragment)
                    }
            }}
            R.id.btnCancel -> requireActivity().onBackPressed()
        }
    }
}