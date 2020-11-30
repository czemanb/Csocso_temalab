package hu.bme.aut.freelancerandroid.repository.repo.transfer

import hu.bme.aut.freelancerandroid.repository.dto.TransferDto
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager


class TransferRepository {

    suspend fun fetchTransfer(authHeader:String?) = NetworkManager.freelancerApi.fetchTransfer(authHeader)


    suspend fun addTransfer(authHeader :String?, transfer: TransferDto) = NetworkManager.freelancerApi.addTransfer(authHeader, transfer)


    suspend fun deleteTransfer(authHeader :String?, transferId: Long) = NetworkManager.freelancerApi.deleteTransfer(authHeader, transferId)

    suspend fun fetchUserTransfer(authHeader: String?, userId: Long) = NetworkManager.freelancerApi.fetchUserTransfer(authHeader, userId)



    suspend fun getVehicles( vehicleId: Long) = NetworkManager.freelancerApi.getVehicles(vehicleId)

    suspend fun fetchTransferNavigationUrl(authHeader:String?, transferId: Long, originLat: Double, originLong: Double) =
        NetworkManager.freelancerApi.fetchTransferNavigationUrl(authHeader, transferId, originLat, originLong)

//    suspend fun fetchTransferPackages( transferId: Long): List<Package>{
//        val packages: MutableList<Package> = mutableListOf()
//        val response = NetworkManager.freelancerApi.fetchTransferPackages(transferId)
//        if (response.code() == 200) {
//            packages.addAll(response.body()!!)
//        }
//
//        return packages
//    }
}