package hu.bme.aut.freelancerandroid

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import hu.bme.aut.freelancerandroid.data.Packages
import hu.bme.aut.freelancerandroid.fragments.*
import hu.bme.aut.freelancerandroid.repository.dto.PackDto
import hu.bme.aut.freelancerandroid.repository.dto.TransferDto
import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import hu.bme.aut.freelancerandroid.repository.repo.pack.PackRepository
import hu.bme.aut.freelancerandroid.repository.repo.transfer.TransferRepository
import hu.bme.aut.freelancerandroid.repository.repo.vehicle.VehicleRepository
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModelProvedirFactory
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModel
import hu.bme.aut.freelancerandroid.ui.transfer.TransferViewModelProviderFactory
import hu.bme.aut.freelancerandroid.ui.vehicles.VehicleViewModel
import hu.bme.aut.freelancerandroid.ui.vehicles.VehicleViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_application.*
import kotlinx.android.synthetic.main.fragment_vehicle_screen.*
import kotlinx.android.synthetic.main.nav_view.*
import kotlin.concurrent.thread

lateinit var toggle: ActionBarDrawerToggle

class ApplicationActivity : AppCompatActivity(), AddPackageDialogFragment.NewPackageItemDialogListener,
AddTransportDialogFragment.NewTransportItemDialogListener, AddTruckDialogFragment.NewTruckItemDialogListener{

    lateinit var packViewModel: PackViewModel
    lateinit var transferViewModel: TransferViewModel
    lateinit var vehicleViewModel: VehicleViewModel
    var gpsEnabled = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkPermission()
        checkGpsEnabled()
        setContentView(R.layout.activity_application)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        setTitle("Home")

        val packsRepository = PackRepository()
        val packViewModelProviderFactory = PackViewModelProvedirFactory(packsRepository)
        packViewModel = ViewModelProvider(this, packViewModelProviderFactory).get(PackViewModel::class.java)

        val transferRepository = TransferRepository()
        val transferViewModelProviderFactory = TransferViewModelProviderFactory(transferRepository)
        transferViewModel = ViewModelProvider(this, transferViewModelProviderFactory).get(TransferViewModel::class.java)

        val vehicleRepository = VehicleRepository()
        val vehicleViewModelProviderFactory = VehicleViewModelProviderFactory(vehicleRepository)
        vehicleViewModel = ViewModelProvider(this, vehicleViewModelProviderFactory).get(VehicleViewModel::class.java)

        navView.setupWithNavController(nav_host_fragment_application.findNavController())
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }
    
    override fun onPackageCreated(newItem: PackDto?) {
        packViewModel.addPackage(newItem!!)
    }


  override fun onTruckCreated(newItem: VehicleDto) {
        vehicleViewModel.addVehicle(newItem)
  }
    override fun onTransportCreated(newItem: TransferDto) {
        transferViewModel.addTransfer(newItem)
    }

    private fun locationEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    private fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this,
                    "I need it to access location", Toast.LENGTH_SHORT).show()
            }
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1)
        }
    }
    private fun checkGpsEnabled() {
        if (!this.locationEnabled()) {
            buildAlertMessageNoGps()
        } else {
            gpsEnabled = true
        }
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            2 -> {
                gpsEnabled = true
            }
        }
    }

    private fun buildAlertMessageNoGps() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("This application requires GPS to work properly, do you want to enable it?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _: DialogInterface, _: Int ->
                val enableGpsIntent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivityForResult(enableGpsIntent,
                    2
                )
            }
            .create()
            .show()
    }
}