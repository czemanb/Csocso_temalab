package hu.bme.aut.freelancerandroid.ui.user.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.LoginActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.response.UserResponse

import hu.bme.aut.freelancerandroid.ui.user.LoginViewModel
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var user: UserResponse
    private var hasUser=false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "Profile"
        loginViewModel = (activity as ApplicationActivity).loginViewModel
        loginViewModel.getUser()
        loginViewModel.activeUserData.observe(viewLifecycleOwner) { response ->
            response.data?.let { userResponse ->
                user = userResponse
                init()
            }
        }

        btnBack.setOnClickListener(this)
    }

    fun init(){
        tvName.text = user.name
        tvEmail.text = user.email
        tvPhoneNumber.text = user.phonenumber
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBack -> requireActivity().onBackPressed()
        }
    }

    companion object{
        const val TAG = "ProfileFragment"
    }
}