package hu.bme.aut.freelancerandroid.repository.repo.transfer

import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager


class TransferRepository {

    suspend fun fetchTransfer(authHeader:String?) = NetworkManager.freelancerApi.fetchTransfer(authHeader)


    suspend fun addTransfer(transfer: Transfer){
        NetworkManager.freelancerApi.addTransfer(transfer)
    }

    suspend fun deleteTransfer(transferId: Long){
        NetworkManager.freelancerApi.deleteTransfer(transferId)
    }


    suspend fun getVehicles( vehicleId: Long) = NetworkManager.freelancerApi.getVehicles(vehicleId)


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