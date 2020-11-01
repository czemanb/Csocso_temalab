package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.freelancerandroid.adapter.PackageListAdapater
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.fragments.MessageFragment
import kotlinx.android.synthetic.main.activity_package_sreen.*
import kotlin.concurrent.thread

class PackageSrceen : AppCompatActivity(), PackageListAdapater.PackageItemClickListener, MessageFragment.NewPackageItemDialogListener   {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PackageListAdapater

    override fun onItemChanged(item: Packages) {
       /* thread {
            database.shoppingItemDao().update(item)
            Log.d("MainActivity", "ShoppingItem update was successful")
        }*/
    }

    override fun onPackageCreated(newItem: Packages) {
        thread {
            runOnUiThread {
                adapter.addPackage(newItem)
            }
        }
    }

    companion object {
        val KEY_MSG = "KEY_MSG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_package_sreen)

        btnDialog1.setOnClickListener{
            MessageFragment().show(
                supportFragmentManager,
                MessageFragment.TAG
            )
        }

        initRecyclerView()
    }

    private fun initRecyclerView(){
        recyclerView = rwPackages
        adapter = PackageListAdapater(this)
        //loadItemsInBackground()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}