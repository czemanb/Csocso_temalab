package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.toggle
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.fragment_home_screen.*
import kotlinx.android.synthetic.main.nav_view.*

class HomeFragment  : Fragment(R.layout.fragment_home_screen){

    private lateinit var navConroller: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navConroller = Navigation.findNavController(view)

        package_button.setOnClickListener {
            navConroller.navigate(R.id.action_homeFragment_to_packageScreenFragment)
        }

        /*transport_button.setOnClickListener {
            requireActivity().setTitle("Transports")
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.root_container, TransportScreenFragment())
                .commitAllowingStateLoss()
        }*/

        transport_button.setOnClickListener {
            navConroller.navigate(R.id.action_homeFragment_to_transportScreenFragment)
        }

        vehicle_button.setOnClickListener {
            navConroller.navigate(R.id.action_homeFragment_to_vehicleScreenFragment)
        }
    }
}
