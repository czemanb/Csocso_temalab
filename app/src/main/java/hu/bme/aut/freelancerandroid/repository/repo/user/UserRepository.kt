package hu.bme.aut.freelancerandroid.repository.repo.user


import hu.bme.aut.freelancerandroid.repository.model.*
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager


class UserRepository{

    //var job: CompletableJob? = null

    suspend fun fetchUsers(): List<User>{
        val users: MutableList<User> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchUsers()
        if (response.code() == 200) {
            users.addAll(response.body()!!)
        }

        return users
    }


    suspend fun addUser(user: RegisterData) = NetworkManager.freelancerApi.addUser(user)



    suspend fun deleteUser(userId: Long) {
        NetworkManager.freelancerApi.deleteUser(userId)
    }



    
    suspend fun loginUser(loginData: LoginData) = NetworkManager.freelancerApi.loginUser(loginData)


//    suspend fun fetchUserPackages(userId: Long): List<Package>{ /todo
//        val packages: MutableList<Package> = mutableListOf()
//        val response = NetworkManager.freelancerApi.fetchUserPackages(userId)
//        if (response.code() == 200) {
//            packages.addAll(response.body()!!)
//        }
//        return packages
//    }
//
//
//    suspend fun fetchUserTransfer(userId: Long): List<Transfer>{
//        val transfers: MutableList<Transfer> = mutableListOf()
//        val response = NetworkManager.freelancerApi.fetchUserTransfer(userId)
//        if (response.code() == 200) {
//            transfers.addAll(response.body()!!)
//        }
//        return transfers
//    }
//
//
//    suspend fun fetchUserVehicles(userId: Long): List<Vehicle>{
//        val vehicles: MutableList<Vehicle> = mutableListOf()
//        val response = NetworkManager.freelancerApi.fetchUserVehicles(userId)
//        if (response.code() == 200) {
//            vehicles.addAll(response.body()!!)
//        }
//        return vehicles
//    }
}