package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.isGone
import androidx.core.view.isVisible
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.fragments.*
import hu.bme.aut.freelancerandroid.model.Transfer
import hu.bme.aut.freelancerandroid.model.Vehicle
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.fragment_vehicle_screen.*
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

        setTitle("Home")

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, HomeFragment())
                        .commitAllowingStateLoss()
                    setTitle("Home")
                }
                R.id.item2 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, PackageScreenFragment())
                        .commitAllowingStateLoss()
                    setTitle("Packages")
                }
                R.id.item3 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, TransportScreenFragment())
                        .commitAllowingStateLoss()
                    setTitle("Transports")
                }
                R.id.item4 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, VehicleScreenFragment())
                        .commitAllowingStateLoss()
                    setTitle("Vehicles")
                }
                R.id.item5 -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.root_container, ProfileFragment())
                        .commitAllowingStateLoss()
                    setTitle("Profile")
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
                var noPackage: TextView
                noPackage = findViewById(R.id.tvNoPackage)
                if(PackageScreenFragment.adapter.getItemCount() != 0)
                    noPackage.isGone = true
                else{
                    noPackage.isGone = false
                    noPackage.isVisible = true
                }
            }
        }
    }

    override fun onTransportCreated(newItem: Transfer) {
        thread {
            runOnUiThread {
                TransportScreenFragment.adapter.addTransport(newItem)
                var noTransport: TextView
                noTransport = findViewById(R.id.tvNoTransport)
                if(TransportScreenFragment.adapter.getItemCount() != 0)
                    noTransport.isGone = true
                else{
                    noTransport.isGone = false
                    noTransport.isVisible = true
                }
            }
        }
    }

    override fun onTruckCreated(newItem: Vehicle) {
        thread {
            runOnUiThread {
                VehicleScreenFragment.adapter.addTruck(newItem)
                var noVehicle: TextView
                noVehicle = findViewById(R.id.tvNoVehicle)
                if(VehicleScreenFragment.adapter.getItemCount() != 0)
                    noVehicle.isGone = true
                else{
                    noVehicle.isGone = false
                    noVehicle.isVisible = true
                }
            }
        }
    }
}