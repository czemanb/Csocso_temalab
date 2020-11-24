package hu.bme.aut.freelancerandroid.repository.repo.pack


import hu.bme.aut.freelancerandroid.repository.network.NetworkManager
import hu.bme.aut.freelancerandroid.repository.model.Package

class PackRepository {


    suspend fun fetchPackages(authHeader :String?)= NetworkManager.freelancerApi.fetchPackages(authHeader)

    suspend fun fetchTransferPackages(transferId: Long) = NetworkManager.freelancerApi.fetchTransferPackages(transferId)


    suspend fun addPackage( pack: Package){
        NetworkManager.freelancerApi.addPackage(pack)
    }


    suspend fun deletePackage(packageId: Long){
            NetworkManager.freelancerApi.deletePackage(packageId)
    }
}