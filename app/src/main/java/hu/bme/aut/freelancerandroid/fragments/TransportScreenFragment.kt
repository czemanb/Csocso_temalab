package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.adapter.TransportListAdapater
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_package_screen.*
import kotlinx.android.synthetic.main.fragment_package_screen.rwPackages
import kotlinx.android.synthetic.main.fragment_transport_screen.*
import kotlin.concurrent.thread

class TransportScreenFragment : Fragment(R.layout.fragment_transport_screen)  , TransportListAdapater.TransportItemClickListener {
    private lateinit var recyclerView: RecyclerView
    lateinit var transferViewModel: TransferViewModel

    val TAG = "TransferScreenFragment"

    companion object{
        public lateinit var adapter: TransportListAdapater
    }

    override fun onItemClicked(item: Transfer, position: Int) {

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
        transferViewModel = (activity as ApplicationActivity).transferViewModel
        transferViewModel.transfers.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { transferResponse ->
                       adapter.transports.submitList(transferResponse)
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