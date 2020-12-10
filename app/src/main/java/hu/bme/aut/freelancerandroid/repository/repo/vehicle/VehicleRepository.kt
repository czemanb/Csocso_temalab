package hu.bme.aut.freelancerandroid.repository.repo.vehicle


import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto
import hu.bme.aut.freelancerandroid.network.NetworkManager



class VehicleRepository {
    suspend fun fetchVehicle(authHeader:String?) = NetworkManager.freelancerApi.fetchVehicle(authHeader)

    suspend fun addVehicle(authHeader:String?, vehicle: VehicleDto) = NetworkManager.freelancerApi.addVehicle(authHeader,vehicle)

    suspend fun deleteVehicle(authHeader:String?, vehicleId: Long) = NetworkManager.freelancerApi.deleteVehicle(authHeader, vehicleId)

    suspend fun fetchUserVehicle(authHeader: String?, userId: Long) = NetworkManager.freelancerApi.fetchUserVehicles(authHeader, userId)



//    suspend fun getVehicles( vehicleId: Long): List<Transfer> {
//        val transfers: MutableList<Transfer> = mutableListOf()
//        val response = NetworkManager.freelancerApi.getVehicles(vehicleId)
//        if (response.code() == 200)
//            transfers.addAll(response.body()!!)
//        return transfers
//    }
}