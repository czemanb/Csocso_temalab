package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.fragments.*
import hu.bme.aut.freelancerandroid.model.Transfer
import hu.bme.aut.freelancerandroid.model.Vehicle
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.nav_view.*
import kotlin.concurrent.thread

lateinit var toggle: ActionBarDrawerToggle

class ApplicationActivity : AppCompatActivity(), AddPackageDialogFragment.NewPackageItemDialogListener,
AddTransportDialogFragment.NewTransportItemDialogListener, AddTruckDialogFragment.NewTruckItemDialogListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_application)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, HomeFragment())
            .commitAllowingStateLoss()

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, HomeFragment())
                        .commitAllowingStateLoss()
                }
                R.id.item2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, PackageScreenFragment())
                        .commitAllowingStateLoss()
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, TransportScreenFragment())
                        .commitAllowingStateLoss()
                }
                R.id.item4 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, VehicleScreenFragment())
                        .commitAllowingStateLoss()
                }
                R.id.item5 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, ProfileFragment())
                        .commitAllowingStateLoss()
                }
                R.id.item6 -> {
                    //log out
                }
            }
            true
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    override fun onPackageCreated(newItem: Packages) {
        thread {
            runOnUiThread {
                PackageScreenFragment.adapter.addPackage(newItem)
            }
        }
    }

    override fun onTransportCreated(newItem: Transfer) {
        thread {
            runOnUiThread {
                TransportScreenFragment.adapter.addTransport(newItem)
            }
        }
    }

    override fun onTruckCreated(newItem: Vehicle) {
        thread {
            runOnUiThread {
                VehicleScreenFragment.adapter.addTruck(newItem)
            }
        }
    }
}