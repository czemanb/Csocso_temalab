package hu.bme.aut.freelancerandroid.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.R
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.data.Packages
import kotlinx.android.synthetic.main.fragment_package_screen.*

class PackageScreenFragment : Fragment(R.layout.fragment_package_screen) , PackageListAdapater.PackageItemClickListener {
    private lateinit var recyclerView: RecyclerView
    companion object{
        public lateinit var adapter: PackageListAdapater
    }

    override fun onItemChanged(item: Packages) {
        /* thread {
             database.shoppingItemDao().update(item)
             Log.d("MainActivity", "ShoppingItem update was successful")
         }*/
    }

    private fun initRecyclerView(){
        recyclerView = rwPackages
        adapter = PackageListAdapater(this)
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
        btnDialog1.setOnClickListener{
            AddPackageDialogFragment().show(
                requireActivity().supportFragmentManager,
                AddPackageDialogFragment.TAG
            )
        }
        initRecyclerView()
    }
}