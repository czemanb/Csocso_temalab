package hu.bme.aut.freelancerandroid.ui.transfer.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.TransportListAdapater
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_package_screen.rwPackages
import kotlinx.android.synthetic.main.fragment_transport_screen.*


class TransportScreenFragment : Fragment(R.layout.fragment_transport_screen),
    TransportListAdapater.TransportItemClickListener {
    private lateinit var recyclerView: RecyclerView
    private lateinit var transferViewModel: TransferViewModel
    private lateinit var navConroller: NavController

    private val TAG = "TransferScreenFragment"

    companion object{
        lateinit var adapter: TransportListAdapater
    }

    private fun initRecyclerView(){
        recyclerView = rwPackages
        adapter = TransportListAdapater(this)
        recyclerView.adapter = adapter
    }

    private fun checkIfThereIsTransport(view: View){
        val noTransport: ConstraintLayout
        noTransport = view.findViewById(R.id.clNoTransport)
        if(adapter.itemCount == 0) {
            noTransport.isGone = false
            noTransport.isVisible = true
        }
        else {
            noTransport.isGone = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().title = "Transports"

        btnDialog.setOnClickListener{
            AddTransportDialogFragment().show(
                requireActivity().supportFragmentManager,
                AddTransportDialogFragment.TAG
            )
        }

        navConroller = Navigation.findNavController(view)
        initRecyclerView()
        transferViewModel = (activity as ApplicationActivity).transferViewModel
        transferViewModel.transfers.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { transferResponse ->
                        adapter.transports.submitList(transferResponse)
                        checkIfThereIsTransport(view)
                        //adapter.transports.addAll(transferResponse)
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
                TransportScreenFragmentDirections.actionTransportScreenFragmentToPackagesOfTransportFragment(
                    it
                )
            navConroller.navigate(action)
        }
    }

    override fun onItemDelete(item: Transfer) {
        transferViewModel.deleteTransfer(item.id)
    }
}