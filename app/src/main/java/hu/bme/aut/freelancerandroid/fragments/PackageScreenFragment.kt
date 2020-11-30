package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.ApplicationActivity
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.android.synthetic.main.fragment_package_screen.*

class PackageScreenFragment : Fragment(R.layout.fragment_package_screen) , PackageListAdapater.PackageItemClickListener {
    private lateinit var recyclerView: RecyclerView

    lateinit var packViewModel: PackViewModel

    val TAG = "PackageScreenFragment"

    companion object{
        lateinit var adapter: PackageListAdapater
    }

    override fun onItemDelete(item: Package) {

        packViewModel.deletePackage(item.id)
    }

    private fun initRecyclerView(){
        recyclerView = rwPackages
        adapter = PackageListAdapater(R.layout.package_row,this)
        //loadItemsInBackground()
        //recyclerView.layoutManager = LinearLayoutManager(activity)
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
        initRecyclerView()
        packViewModel = (activity as ApplicationActivity).packViewModel
        packViewModel.packs.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    response.data?.let { packResponse ->
                        adapter.packages.submitList(packResponse)
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

        btnDialog1.setOnClickListener{
            AddPackageDialogFragment().show(
                requireActivity().supportFragmentManager,
                AddPackageDialogFragment.TAG
            )
        }

        requireActivity().setTitle("Packages")

    }
}