package hu.bme.aut.freelancerandroid.ui.vehicles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.freelancerandroid.repository.repo.vehicle.VehicleRepository

class VehicleViewModelProviderFactory(private val vehicleRepository: VehicleRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return VehicleViewModel(vehicleRepository) as T
    }
}