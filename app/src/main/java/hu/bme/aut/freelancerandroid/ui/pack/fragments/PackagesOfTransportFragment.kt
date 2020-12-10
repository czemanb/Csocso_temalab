package hu.bme.aut.freelancerandroid.ui.pack.fragments

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
import hu.bme.aut.freelancerandroid.adapter.SpecialPackageListAdapater
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.response.PackResponse
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import hu.bme.aut.freelancerandroid.repository.model.Package
import kotlinx.android.synthetic.main.fragment_packages_of_transport.*

class PackagesOfTransportFragment  : Fragment(R.layout.fragment_packages_of_transport), SpecialPackageListAdapater.PackageItemClickListener {
    private val args: PackagesOfTransportFragmentArgs by navArgs()
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var packageViewModel: PackViewModel
    private lateinit var adapterWaiting: SpecialPackageListAdapater
    private lateinit var adapterInCar: SpecialPackageListAdapater
    private lateinit var adapterDelivered: SpecialPackageListAdapater
    private lateinit var recyclerViewWaiting: RecyclerView
    private lateinit var recyclerViewInCar: RecyclerView
    private lateinit var recyclerViewDelivered: RecyclerView

    private lateinit var transportPackages: PackResponse
    private var hasPackages: Boolean = false
    private lateinit var transport: Transfer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transferViewModel = (activity as ApplicationActivity).transferViewModel
        packageViewModel = (activity as ApplicationActivity).packViewModel

        transport = args.asd

        packageViewModel.fetchTransferPackages(transport.id)

        packageViewModel.transferPacks.observe(viewLifecycleOwner) { response ->
            response.data?.let { packResponse ->
                transportPackages = packResponse
                hasPackages = true
                initRecyclerView()
            }
        }

        btnMap.setOnClickListener {
            if (hasPackages) {
                val packagesForTransfer = transportPackages.filter { p -> p.transfer?.id == transport.id }
                val names = packagesForTransfer.map { p -> p.name }
                val pickupTimes = packagesForTransfer.map { p -> p.pickupTime ?: "" }
                val deliveryTimes = packagesForTransfer.map { p -> p.deliveryTime ?: "" }
                val destinations = packagesForTransfer.map { p -> LatLng(p.toLat, p.toLong) }
                val pickUpPoints = packagesForTransfer.map { p -> LatLng(p.fromLat, p.fromLong) }

                val action =
                    PackagesOfTransportFragmentDirections.actionPackagesOfTransportFragmentToGoogleMapsFragment(
                        pickUpPoints = pickUpPoints.toTypedArray(),
                        transfer = transport,
                        destinations = destinations.toTypedArray(),
                        names = names.toTypedArray(),
                        pickupTimes = pickupTimes.toTypedArray(),
                        deliveryTimes = deliveryTimes.toTypedArray()
                    )
                findNavController().navigate(action)
            }
        }
    }

    private fun initRecyclerView(){
        recyclerViewWaiting = rwWaiting
        recyclerViewInCar = rwInCar
        recyclerViewDelivered = rwDelivered
        adapterWaiting = SpecialPackageListAdapater(R.layout.package_waiting_row, this)
        adapterInCar = SpecialPackageListAdapater(R.layout.package_in_car_row, this)
        adapterDelivered = SpecialPackageListAdapater(R.layout.package_delivered_row, this)
        recyclerViewWaiting.adapter = adapterWaiting
        recyclerViewInCar.adapter = adapterInCar
        recyclerViewDelivered.adapter = adapterDelivered

        load()

    }

    private fun load(){
        adapterWaiting.packages.submitList(transportPackages.filter { p -> p.status == "WAITING" })
        adapterInCar.packages.submitList(transportPackages.filter { p -> p.status == "INCAR" })
        adapterDelivered.packages.submitList(transportPackages.filter { p -> p.status == "DELIVERED" })
    }

    override fun onArrowUpClicked(item: Package) {
        if(item.status == "DELIVERED"){
            item.status = "INCAR"
            packageViewModel.changePackageStatus(item.id, "INCAR")
        }else if(item.status == "INCAR"){
            item.status = "WAITING"
            packageViewModel.changePackageStatus(item.id, "WAITING")
        }
        load()
    }

    override fun onArrowDownClicked(item: Package) {
        if(item.status == "WAITING"){
            item.status = "INCAR"
            packageViewModel.changePackageStatus(item.id, "INCAR")
        }else if(item.status == "INCAR"){
            item.status = "DELIVERED"
            packageViewModel.changePackageStatus(item.id, "DELIVERED")
        }
        load()
    }

}