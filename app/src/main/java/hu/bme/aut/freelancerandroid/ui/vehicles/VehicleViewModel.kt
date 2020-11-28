package hu.bme.aut.freelancerandroid.ui.vehicles

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto

import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import hu.bme.aut.freelancerandroid.repository.repo.vehicle.VehicleRepository
import hu.bme.aut.freelancerandroid.repository.response.VehicleResponse
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class VehicleViewModel(val vehicleRepository: VehicleRepository): ViewModel() {

    val vehicles: MutableLiveData<Resource<VehicleResponse>> = MutableLiveData()


    init{
        fetchVehicle()
    }
    
    fun fetchVehicle()= viewModelScope.launch {
        vehicles.postValue(Resource.Loading())
        val response = vehicleRepository.fetchVehicle("Bearer " + GlobalVariable.token)
        vehicles.postValue(handleVehicleResponse(response))
    }

    fun addVehicle(vehicle: VehicleDto) = viewModelScope.launch {
        val response = vehicleRepository.addVehicle("Bearer " + GlobalVariable.token, vehicle)
        fetchVehicle()
    }

    fun fetchUserVehicle(vehicleId: Long) = viewModelScope.launch {
        vehicles.postValue(Resource.Loading())
        val response = vehicleRepository.fetchUserVehicle("Bearer " + GlobalVariable.token, vehicleId)
        vehicles.postValue(handleVehicleResponse(response))
    }

    fun deleteVehicle(vehicleId: Long) = viewModelScope.launch {
        vehicleRepository.deleteVehicle("Bearer " + GlobalVariable.token, vehicleId)
        fetchVehicle()
    }

    private fun handleVehicleResponse(response: Response<VehicleResponse>) : Resource<VehicleResponse>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

}