package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.model.LatLng
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.proba.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.repository.response.PackResponse
import hu.bme.aut.freelancerandroid.repository.response.TransferResponse
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_random.*


class RandomFragment : Fragment(R.layout.fragment_random) {

    lateinit var transfers: TransferResponse
    lateinit var packages: PackResponse
    var hasTransfers = false
    var hasPackages = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val transferViewModel = (activity as ApplicationActivity).transferViewModel
        val packageViewModel = (activity as ApplicationActivity).packViewModel

        transferViewModel.transfers.observe(viewLifecycleOwner) { response ->
            response.data?.let { transferResponse ->
                transfers = transferResponse
                hasTransfers = true
            }
        }
        packageViewModel.packs.observe(viewLifecycleOwner) { response ->
            response.data?.let { packResponse ->
                packages = packResponse
                hasPackages = true
            }
        }


        nav_to_google_maps.setOnClickListener {
            if (hasPackages && hasTransfers) {
                val transfer = transfers.find { t -> t.id == 53L }
                if (transfer != null) {
                    val packagesForTransfer = packages.filter { p -> p.transfer?.id == transfer.id }
                    val destinations = packagesForTransfer.map { p -> LatLng(p.toLat, p.toLong) }
                    val pickUpPoints = packagesForTransfer.map { p -> LatLng(p.fromLat, p.fromLong) }

                }
            }
        }
    }
}