package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.repository.response.PackResponse
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import kotlinx.android.synthetic.main.fragment_package_screen.*
import kotlinx.android.synthetic.main.fragment_packages_of_transport.*

class PackagesOfTransportFragment  : Fragment(R.layout.fragment_packages_of_transport),PackageListAdapater.PackageItemClickListener {

    val args: PackagesOfTransportFragmentArgs by navArgs()
    lateinit var transferViewModel: TransferViewModel
    lateinit var packageViewModel: PackViewModel
    lateinit var adapterWaiting: PackageListAdapater
    lateinit var adapterInCar: PackageListAdapater
    lateinit var adapterDelivered: PackageListAdapater
    private lateinit var recyclerViewWaiting: RecyclerView
    private lateinit var recyclerViewInCar: RecyclerView
    private lateinit var recyclerViewDelivered: RecyclerView

    private lateinit var packages: PackResponse

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transferViewModel = (activity as ApplicationActivity).transferViewModel
        packageViewModel = (activity as ApplicationActivity).packViewModel

        val transport = args.asd

        packageViewModel.packs.observe(viewLifecycleOwner) { response ->
            response.data?.let { packResponse ->
                packages = packResponse
                initRecyclerView()
            }
        }
    }

    private fun initRecyclerView(){
        recyclerViewWaiting = rwWaiting
        recyclerViewInCar = rwInCar
        recyclerViewDelivered = rwDelivered
        adapterWaiting = PackageListAdapater(R.layout.package_waiting_row,this)
        adapterInCar = PackageListAdapater(R.layout.package_in_car_row,this)
        adapterDelivered = PackageListAdapater(R.layout.package_delivered_row,this)
        recyclerViewWaiting.adapter = adapterWaiting
        recyclerViewInCar.adapter = adapterInCar
        recyclerViewDelivered.adapter = adapterDelivered

        adapterWaiting.packages.submitList(packages.filter { p -> p.status == "WAITING" })
        adapterInCar.packages.submitList(packages.filter { p -> p.status == "INCAR" })
        adapterDelivered.packages.submitList(packages.filter { p -> p.status == "DELIVERED" })
    }

    override fun onItemDelete(item: Package) {

    }
}