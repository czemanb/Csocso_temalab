package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.User
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.register_form.*

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {
    val user: User =
        User(1243, "John Smith", "johnsmith@gmail.com", "0620345677899", "valami", "valami",true)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().setTitle("Profile")

        tvName.text = user.name
        tvEmail.text = user.email
        tvPhoneNumber.text = user.phonenumber
        btnBack.setOnClickListener(this)
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