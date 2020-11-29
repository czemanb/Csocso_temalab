package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.maps.model.LatLng
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.repository.response.PackResponse
import hu.bme.aut.freelancerandroid.repository.response.TransferResponse
import kotlinx.android.synthetic.main.fragment_packages_of_transport.*

class PackagesOfTransportFragment  : Fragment(R.layout.fragment_packages_of_transport) {

    val args: TransportScreenFragmentArgs by navArgs()
//    lateinit var viewModel: TransferViewModel
    lateinit var adapterWaiting: PackageListAdapater
    lateinit var adapterInCar: PackageListAdapater
    lateinit var adapterDelivered: PackageListAdapater
    private lateinit var recyclerViewWaiting: RecyclerView
    private lateinit var recyclerViewInCar: RecyclerView
    private lateinit var recyclerViewDelivered: RecyclerView

    lateinit var transfers: TransferResponse
    lateinit var packages: PackResponse
    var hasTransfers = false
    var hasPackages = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

//        viewModel = (activity as ApplicationActivity).transferViewModel
//        val transport = args.transfer


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

        btnMap.setOnClickListener {
            if (hasPackages && hasTransfers) {
                val transfer = transfers.find { t -> t.id == 53L }
                if (transfer != null) {
                    val packagesForTransfer = packages.filter { p -> p.transfer?.id == transfer.id }
                    val names = packagesForTransfer.map { p -> p.name }
                    val pickupTimes = packagesForTransfer.map { p -> p.pickupTime ?: "" }
                    val deliveryTimes = packagesForTransfer.map { p -> p.deliveryTime ?: "" }
                    val destinations = packagesForTransfer.map { p -> LatLng(p.toLat, p.toLong) }
                    val pickUpPoints = packagesForTransfer.map { p -> LatLng(p.fromLat, p.fromLong) }

                    val action = PackagesOfTransportFragmentDirections.actionPackagesOfTransportFragmentToGoogleMapsFragment(
                        pickUpPoints = pickUpPoints.toTypedArray(),
                        transfer = transfer,
                        destinations = destinations.toTypedArray(),
                        names = names.toTypedArray(),
                        pickupTimes = pickupTimes.toTypedArray(),
                        deliveryTimes = deliveryTimes.toTypedArray()
                    )
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun initRecyclerView(){
        recyclerViewWaiting = rwWaiting
        recyclerViewInCar = rwInCar
        recyclerViewDelivered = rwDelivered
        adapterWaiting = PackageListAdapater()
        adapterInCar = PackageListAdapater()
        adapterDelivered = PackageListAdapater()
        recyclerViewWaiting.adapter = adapterWaiting
        recyclerViewInCar.adapter = adapterInCar
        recyclerViewDelivered.adapter = adapterDelivered
    }
}