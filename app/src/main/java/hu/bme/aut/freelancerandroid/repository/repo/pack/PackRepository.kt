package hu.bme.aut.freelancerandroid.repository.repo.pack


import hu.bme.aut.freelancerandroid.repository.dto.PackDto
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager

class PackRepository {

    suspend fun fetchPackages(authHeader :String?)= NetworkManager.freelancerApi.fetchPackages(authHeader)

    suspend fun fetchUserPackages(authHeader: String?, userId: Long) = NetworkManager.freelancerApi.fetchUserPackages(authHeader, userId)

    suspend fun fetchTransferPackages(authHeader :String?, transferId: Long) = NetworkManager.freelancerApi.fetchTransferPackages(authHeader,transferId)

    suspend fun changeStatusPackage(authHeader :String?, packageId: Long, status: String)
            = NetworkManager.freelancerApi.changeStatusPackage(authHeader,packageId,status)

    suspend fun addPackage(authHeader :String?, pack: PackDto) = NetworkManager.freelancerApi.addPackage(authHeader,pack)

    suspend fun deletePackage(authHeader :String?, packageId: Long) = NetworkManager.freelancerApi.deletePackage(authHeader, packageId)

}