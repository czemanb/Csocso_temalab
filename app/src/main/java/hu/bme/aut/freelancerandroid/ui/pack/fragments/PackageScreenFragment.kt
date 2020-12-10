package hu.bme.aut.freelancerandroid.ui.pack.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_package_screen.*

class PackageScreenFragment : Fragment(R.layout.fragment_package_screen) , PackageListAdapater.PackageItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var packViewModel: PackViewModel

    private val TAG = "PackageScreenFragment"

    companion object{
        lateinit var adapter: PackageListAdapater
    }

    override fun onItemDelete(item: Package) {

        packViewModel.deletePackage(item.id)
    }

    private fun initRecyclerView(){
        recyclerView = rwPackages
        adapter = PackageListAdapater(R.layout.package_row,this)
        recyclerView.adapter = adapter
    }

    private fun checkIfThereIsPackage(view: View){
        val noPckg: ConstraintLayout
        noPckg = view.findViewById(R.id.noPackage)
        if (adapter.itemCount == 0) {
            noPckg.isGone = false
            noPckg.isVisible = true
        }
        else {
            noPckg.isGone = true
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        packViewModel = (activity as ApplicationActivity).packViewModel
        packViewModel.packs.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { packResponse ->
                        adapter.packages.submitList(packResponse)
                        checkIfThereIsPackage(view)
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

        btnAddPackage.setOnClickListener{
            AddPackageDialogFragment().show(
                requireActivity().supportFragmentManager,
                AddPackageDialogFragment.TAG
            )
        }

        adapter.setOnItemClickListener {
            val action =
                PackageScreenFragmentDirections.actionPackageScreenFragmentToPackageDetailsFragment(
                    it
                )
            findNavController().navigate(action)
        }

        requireActivity().title = "Packages"
    }
}