package hu.bme.aut.freelancerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        //thread {
         //   runOnUiThread {
                packViewModel.addPackage(newItem!!)
               // PackageScreenFragment.adapter.addPackage(newItem) /Todo
//                var noPckg: ConstraintLayout
//               noPckg = findViewById(R.id.noPackage)
//               if(PackageScreenFragment.adapter.getItemCount() != 0)
//                    noPckg.isGone = true
//                else{
//                    noPckg.isGone = false
//                    noPckg.isVisible = true
//                }
          //  }
       // }
    }

    override fun onTransportCreated(newItem: TransferDto) {
//        thread {//todo
//            runOnUiThread {
                //TransportScreenFragment.adapter.addTransport(newItem)
        transferViewModel.addTransfer(newItem)
//                var noTransport: ConstraintLayout
//                noTransport = findViewById(R.id.clNoTransport)
//                if(TransportScreenFragment.adapter.getItemCount() != 0)
//                    noTransport.isGone = true
//                else{
//                    noTransport.isGone = false
//                    noTransport.isVisible = true
//                }
//            }
//        }
    }

    override fun onTruckCreated(newItem: VehicleDto) {
//        thread {
//            runOnUiThread {
                vehicleViewModel.addVehicle(newItem)
//                VehicleScreenFragment.adapter.addTruck()
//                var noVehicle: ConstraintLayout
//                noVehicle = findViewById(R.id.clNoVehicle)
//                if(VehicleScreenFragment.adapter.getItemCount() != 0)
//                    noVehicle.isGone = true
//                else{
//                    noVehicle.isGone = false
//                    noVehicle.isVisible = true
//                }
//            }
//        }
    }


}