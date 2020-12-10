package hu.bme.aut.freelancerandroid.ui.vehicles.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.TruckListAdapter
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import hu.bme.aut.freelancerandroid.ui.vehicles.VehicleViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_vehicle_screen.*

class VehicleScreenFragment  : Fragment(R.layout.fragment_vehicle_screen)  , TruckListAdapter.TruckItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var vehicleViewModel  :VehicleViewModel
    private val TAG = "VehicleScreenFragment"

    companion object{
        lateinit var adapter: TruckListAdapter
    }

    override fun onItemChanged(item: Vehicle) {
        /* thread {
             database.shoppingItemDao().update(item)
             Log.d("LoginActivity", "ShoppingItem update was successful")
         }*/
    }

    override fun onItemDelete(item: Vehicle) {
        Log.e("eee", "valami")
        vehicleViewModel.deleteVehicle(item.id)
    }

    private fun initRecyclerView(){
        recyclerView = rwVehicles
        adapter = TruckListAdapter(this)
        recyclerView.adapter = adapter
    }

    private fun checkIfThereIsVehicle(view: View){
        val noVehicle: ConstraintLayout
        noVehicle = view.findViewById(R.id.clNoVehicle)
        if (adapter.itemCount == 0) {
            noVehicle.isGone = false
            noVehicle.isVisible = true
        }
        else {
            noVehicle.isGone = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Vehicles"

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
                        checkIfThereIsVehicle(view)
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

        adapter.setOnItemClickListener {
            val action =
                VehicleScreenFragmentDirections.actionVehicleScreenFragmentToVehicleDetailsFragment(
                    it
                )
            findNavController().navigate(action)
        }
    }
}