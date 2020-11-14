package hu.bme.aut.freelancerandroid.repository.repo.transfer

import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager
import hu.bme.aut.freelancerandroid.repository.model.Package
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TransferRepository {
    suspend fun fetchTransfer(): List<Transfer>{
        val transfers: MutableList<Transfer> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchTransfer()
        if (response.code() == 200) {
            transfers.addAll(response.body()!!)
        }
        return transfers
    }


    suspend fun addTransfer(transfer: Transfer){
        NetworkManager.freelancerApi.addTransfer(transfer)
    }

    suspend fun deleteTransfer(transferId: Long){
        NetworkManager.freelancerApi.deleteTransfer(transferId)
    }


    suspend fun fetchTransferPackages( transferId: Long): List<Package>{
        val packages: MutableList<Package> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchTransferPackages(transferId)
        if (response.code() == 200) {
            packages.addAll(response.body()!!)
        }

        return packages
    }
}