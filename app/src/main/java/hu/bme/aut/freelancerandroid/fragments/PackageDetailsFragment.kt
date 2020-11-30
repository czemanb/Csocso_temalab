package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.model.LatLng
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.repository.model.Package
import kotlinx.android.synthetic.main.fragment_package_details.*
import kotlinx.android.synthetic.main.fragment_profile.*

class PackageDetailsFragment: Fragment(R.layout.fragment_package_details), View.OnClickListener{
    private val args: PackageDetailsFragmentArgs by navArgs()
    private lateinit var status: TextView
    private lateinit var deadline: TextView
    private lateinit var weight: TextView
    private lateinit var size: TextView
    private lateinit var city: TextView
    private lateinit var value: TextView
    private lateinit var name: TextView
    private lateinit var pack: Package

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pack = args.pack
        status = view.findViewById(R.id.pacStatus)
        deadline = view.findViewById(R.id.packDeadline)
        weight= view.findViewById(R.id.packWeight)
        size = view.findViewById(R.id.packSize)
        city = view.findViewById(R.id.packCity)
        value = view.findViewById(R.id.packValue)
        name = view.findViewById(R.id.packName)
        status.text = pack.status
        deadline.text = pack.dateLimit
        weight.text = "${pack.weight.toString()} kg"
        size.text = pack.size
        city.text = pack.town.name
        value.text = "${pack.value.toString()} HUF"
        name.text = pack.name

        btnPackageShowInMap.setOnClickListener {
            val names: MutableList<String> = mutableListOf()
            names.add(pack.name)
            val pickupTimes: MutableList<String> = mutableListOf()
            pickupTimes.add(pack.pickupTime ?: "")
            val deliveryTimes: MutableList<String> = mutableListOf()
            deliveryTimes.add(pack.deliveryTime ?: "")
            val destinations: MutableList<LatLng> = mutableListOf()
            destinations.add(LatLng(pack.toLat, pack.toLong))
            val pickUpPoints: MutableList<LatLng> = mutableListOf()
            pickUpPoints.add(LatLng(pack.fromLat, pack.fromLong))

            val action = PackageDetailsFragmentDirections.actionPackageDetailsFragmentToGoogleMapsFragment(
                pickUpPoints = pickUpPoints.toTypedArray(),
                transfer = null,
                destinations = destinations.toTypedArray(),
                names = names.toTypedArray(),
                pickupTimes = pickupTimes.toTypedArray(),
                deliveryTimes = deliveryTimes.toTypedArray()
            )
            findNavController().navigate(action)
        }
      
        btnBackButton.setOnClickListener(this)
    }
  
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnBackButton -> requireActivity().onBackPressed()
        }
    }
}