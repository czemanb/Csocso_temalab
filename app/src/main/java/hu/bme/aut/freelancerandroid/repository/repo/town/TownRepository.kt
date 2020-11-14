package hu.bme.aut.freelancerandroid.repository.repo.town

import hu.bme.aut.freelancerandroid.repository.model.Town
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

class TownRepository {
    suspend fun fetchTowns(): List<Town>{
        val towns: MutableList<Town> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchTowns()
        if (response.code() == 200) {
            towns.addAll(response.body()!!)
        }
        return towns
    }

    suspend fun addTown(town: Town){
        NetworkManager.freelancerApi.addTown(town)
    }

    suspend fun deleteTown( townId: Long){
        NetworkManager.freelancerApi.deleteTown(townId)
    }

    suspend fun getPackagesInTown(townId: Long): List<Package>{
        val packages: MutableList<Package> = mutableListOf()
        val response = NetworkManager.freelancerApi.getPackagesInTown(townId)
        if (response.code() == 200) {
            packages.addAll(response.body()!!)
        }
        return packages
    }

    suspend fun getTransfersInTown( townId: Long): List<Transfer>{
        val transfers: MutableList<Transfer> = mutableListOf()
        val response = NetworkManager.freelancerApi.getTransfersInTown(townId)
        if (response.code() == 200) {
            transfers.addAll(response.body()!!)
        }
        return transfers
    }
}