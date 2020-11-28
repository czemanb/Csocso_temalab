package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import kotlinx.android.synthetic.main.fragment_package_screen.*
import kotlinx.android.synthetic.main.fragment_packages_of_transport.*

class PackagesOfTransportFragment  : Fragment(R.layout.fragment_packages_of_transport) {

    val args: TransportScreenFragmentArgs by navArgs()
    lateinit var viewModel: TransferViewModel
    lateinit var adapterWaiting: PackageListAdapater
    lateinit var adapterInCar: PackageListAdapater
    lateinit var adapterDelivered: PackageListAdapater
    private lateinit var recyclerViewWaiting: RecyclerView
    private lateinit var recyclerViewInCar: RecyclerView
    private lateinit var recyclerViewDelivered: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel = (activity as ApplicationActivity).transferViewModel
//        val transport = args.transfer
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