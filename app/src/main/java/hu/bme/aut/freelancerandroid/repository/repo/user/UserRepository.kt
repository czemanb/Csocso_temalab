package hu.bme.aut.freelancerandroid.repository.repo.user

import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.User
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import hu.bme.aut.freelancerandroid.repository.network.NetworkManager
import hu.bme.aut.freelancerandroid.repository.model.Package

class UserRepository(){

    suspend fun fetchUsers(): List<User>{
        val users: MutableList<User> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchUsers()
        if (response.code() == 200) {
            users.addAll(response.body()!!)
        }

        return users
    }


    suspend fun addUser(user: User){
        NetworkManager.freelancerApi.addUser(user)
    }


    suspend fun deleteUser(userId: Long) {
        NetworkManager.freelancerApi.deleteUser(userId)
    }

    suspend fun getUser(userId: Long): User {
        lateinit var user : User
        val response = NetworkManager.freelancerApi.getUser(userId)
        if (response.code()==200)
            user = (response.body()!!)
        return user
    }


    //suspend fun loginUser(@Body user: User): Call<User> //ToDo


    suspend fun fetchUserPackages(userId: Long): List<Package>{
        val packages: MutableList<Package> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchUserPackages(userId)
        if (response.code() == 200) {
            packages.addAll(response.body()!!)
        }
        return packages
    }


    suspend fun fetchUserTransfer(userId: Long): List<Transfer>{
        val transfers: MutableList<Transfer> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchUserTransfer(userId)
        if (response.code() == 200) {
            transfers.addAll(response.body()!!)
        }
        return transfers
    }


    suspend fun fetchUserVehicles(userId: Long): List<Vehicle>{
        val vehicles: MutableList<Vehicle> = mutableListOf()
        val response = NetworkManager.freelancerApi.fetchUserVehicles(userId)
        if (response.code() == 200) {
            vehicles.addAll(response.body()!!)
        }
        return vehicles
    }
}