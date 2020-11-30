package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import hu.bme.aut.freelancerandroid.R

class VehicleDetailsFragment : Fragment(R.layout.fragment_vehicle_details) {

    val args: VehicleDetailsFragmentArgs by navArgs()
    private lateinit var length: TextView
    private lateinit var height: TextView
    private lateinit var width: TextView
    private lateinit var name: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        name = view.findViewById(R.id.tvVehicleName)
        width = view.findViewById(R.id.tvVehicleWidth)
        length = view.findViewById(R.id.tvVehicleLength)
        height = view.findViewById(R.id.tvVehicleHeight)

        val vehicle = args.vehicle

        name.text = vehicle.name
        width.text = "${vehicle.x.toString()} cm"
        length.text = "${vehicle.y.toString()} cm"
        height.text = "${vehicle.z.toString()} cm"
    }
}