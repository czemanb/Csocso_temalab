package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.TransportListAdapater
import hu.bme.aut.freelancerandroid.adapter.TruckListAdapter
import hu.bme.aut.freelancerandroid.model.Transfer
import hu.bme.aut.freelancerandroid.model.Vehicle
import kotlinx.android.synthetic.main.fragment_package_screen.*
import kotlinx.android.synthetic.main.fragment_package_screen.rwPackages
import kotlinx.android.synthetic.main.fragment_transport_screen.*
import kotlinx.android.synthetic.main.fragment_vehicle_screen.*

class VehicleScreenFragment  : Fragment(R.layout.fragment_vehicle_screen)  , TruckListAdapter.TruckItemClickListener {
    private lateinit var recyclerView: RecyclerView
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
    }
}