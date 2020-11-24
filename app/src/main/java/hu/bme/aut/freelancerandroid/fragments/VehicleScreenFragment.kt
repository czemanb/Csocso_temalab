package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.TransportListAdapater
import hu.bme.aut.freelancerandroid.adapter.TruckListAdapter
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import hu.bme.aut.freelancerandroid.ui.vehicles.VehicleViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_package_screen.*
import kotlinx.android.synthetic.main.fragment_package_screen.rwPackages
import kotlinx.android.synthetic.main.fragment_transport_screen.*
import kotlinx.android.synthetic.main.fragment_vehicle_screen.*

class VehicleScreenFragment  : Fragment(R.layout.fragment_vehicle_screen)  , TruckListAdapter.TruckItemClickListener {
    private lateinit var recyclerView: RecyclerView
    lateinit var vehicleViewModel  :VehicleViewModel
    val TAG = "VehicleScreenFragment"

    companion object{
        public lateinit var adapter: TruckListAdapter
    }

    override fun onItemChanged(item: Vehicle) {
        /* thread {
             database.shoppingItemDao().update(item)
             Log.d("LoginActivity", "ShoppingItem update was successful")
         }*/
    }

    private fun initRecyclerView(){
        recyclerView = rwVehicles
        adapter = TruckListAdapter(this)
        //loadItemsInBackground()
        recyclerView.adapter = adapter
    }

    /*private fun loadItemsInBackground() {
        thread {
            val items = database.shoppingItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnDialogVehicles.setOnClickListener{
            AddTruckDialogFragment().show(
                requireActivity().supportFragmentManager,
                AddTruckDialogFragment.TAG
            )
        }
        initRecyclerView()
        vehicleViewModel = (activity as ApplicationActivity).vehicleViewModel
        vehicleViewModel.vehicles.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { vehicleResponse ->
                        adapter.trucks.submitList(vehicleResponse)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {

                }

            }
        })


    }
}