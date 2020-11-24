package hu.bme.aut.freelancerandroid.repository.repo.vehicle


import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager



class VehicleRepository {

    suspend fun fetchVehicle(authHeader:String?) = NetworkManager.freelancerApi.fetchVehicle(authHeader)

    suspend fun addVehicle(vehicle: Vehicle){
        NetworkManager.freelancerApi.addVehicle(vehicle)
    }


    suspend fun deleteVehicle(vehicleId: Long){
        NetworkManager.freelancerApi.deleteVehicle(vehicleId)
    }




//    suspend fun getVehicles( vehicleId: Long): List<Transfer> {
//        val transfers: MutableList<Transfer> = mutableListOf()
//        val response = NetworkManager.freelancerApi.getVehicles(vehicleId)
//        if (response.code() == 200)
//            transfers.addAll(response.body()!!)
//        return transfers
//    }
}