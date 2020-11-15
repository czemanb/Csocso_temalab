package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.adapter.TransportListAdapater
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.model.Transfer
import kotlinx.android.synthetic.main.fragment_package_screen.*
import kotlinx.android.synthetic.main.fragment_package_screen.rwPackages
import kotlinx.android.synthetic.main.fragment_transport_screen.*

class TransportScreenFragment : Fragment(R.layout.fragment_transport_screen)  , TransportListAdapater.TransportItemClickListener {
    private lateinit var recyclerView: RecyclerView
    companion object{
        public lateinit var adapter: TransportListAdapater
    }

    override fun onItemChanged(item: Transfer) {
        /* thread {
             database.shoppingItemDao().update(item)
             Log.d("MainActivity", "ShoppingItem update was successful")
         }*/
    }

    private fun initRecyclerView(){
        recyclerView = rwPackages
        adapter = TransportListAdapater(this)
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
        btnDialog.setOnClickListener{
            AddTransportDialogFragment().show(
                requireActivity().supportFragmentManager,
                AddTransportDialogFragment.TAG
            )
        }
        initRecyclerView()
    }
}