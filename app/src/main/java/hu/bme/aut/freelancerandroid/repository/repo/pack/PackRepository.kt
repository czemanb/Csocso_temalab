package hu.bme.aut.freelancerandroid.repository.repo.pack


import hu.bme.aut.freelancerandroid.repository.network.NetworkManager
import hu.bme.aut.freelancerandroid.repository.model.Package

class PackRepository {

    suspend fun fetchPackages(): List<Package>{
        val packages: MutableList<Package> = mutableListOf()
            val response = NetworkManager.freelancerApi.fetchPackages()
            if (response.code() == 200){
            packages.addAll(response.body()!!)
            }
        return packages
    }


    suspend fun addPackage( pack: Package){
        NetworkManager.freelancerApi.addPackage(pack)
    }


    suspend fun deletePackage(packageId: Long){
            NetworkManager.freelancerApi.deletePackage(packageId)
    }
}