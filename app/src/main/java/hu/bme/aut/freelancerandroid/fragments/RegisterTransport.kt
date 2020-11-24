package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import hu.bme.aut.freelancerandroid.R
import kotlinx.android.synthetic.main.register_form.*
import kotlinx.android.synthetic.main.register_transport.*

class RegisterTransport : Fragment(R.layout.register_transport), View.OnClickListener, DatePickerDialogFragment.OnDateSelectedListener {
    lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnChooseDate.setOnClickListener {
            getFragmentManager()?.let { it1 -> DatePickerDialogFragment().show(it1,"DATE_TAG") }
        }
    }

    override fun onClick(v: View?) {

    }

    override fun onDateSelected(year: Int, month: Int, day: Int) {
        TODO("Not yet implemented")
    }
}

