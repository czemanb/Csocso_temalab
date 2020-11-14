package hu.bme.aut.freelancerandroid.repository.network
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.model.User
import hu.bme.aut.freelancerandroid.repository.model.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import hu.bme.aut.freelancerandroid.repository.model.Package


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val SERVICE_URL = "https://freelancer-carrier-spring.herokuapp.com/?fbclid=IwAR1CzH1H6hDuK860Y2Dr4vgi3cYN0nJwfjAY-a8FbDtbChSAu4abvRHnzkY"

    public val freelancerApi: FreelancerApi

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        freelancerApi = retrofit.create(FreelancerApi::class.java)
    }

    //User
//     fun fetchUsers(): List<User>{
//         val users: MutableList<User> = mutableListOf()
//          GlobalScope.launch(Dispatchers.IO) {
//             val response = freelancerApi.fetchUsers()
//              if (response.code() == 200) {
//                  users.addAll(response.body()!!)
//              }
//         }
//         return users
//     }
//
//
//   fun addUser(user: User){
//       GlobalScope.launch(Dispatchers.IO) {
//          freelancerApi.addUser(user)
//       }
//   }
//
//
//   fun deleteUser(userId: Long) {
//       GlobalScope.launch(Dispatchers.IO) {
//           freelancerApi.deleteUser(userId)
//       }
//   }
//
//    fun getUser(userId: Long): User {
//        lateinit var user : User
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.getUser(userId)
//            user = (response.body()!!)
//        }
//        return user
//    }
//
//
//    //suspend fun loginUser(@Body user: User): Call<User> //ToDo
//
//
//    fun fetchUserPackages(userId: Long): List<Package>{
//        val packages: MutableList<Package> = mutableListOf()
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.fetchUserPackages(userId)
//            packages.addAll(response.body()!!)
//        }
//        return packages
//    }
//
//
//    fun fetchUserTransfer(userId: Long): List<Transfer>{
//        val transfers: MutableList<Transfer> = mutableListOf()
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.fetchUserTransfer(userId)
//            transfers.addAll(response.body()!!)
//        }
//        return transfers
//    }
//
//
//    fun fetchUserVehicles(userId: Long): List<Vehicle>{
//        val vehicles: MutableList<Vehicle> = mutableListOf()
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.fetchUserVehicles(userId)
//            vehicles.addAll(response.body()!!)
//        }
//        return vehicles
//    }
//
//    //Package
//    fun fetchPackages(): List<Package>{
//        val packages: MutableList<Package> = mutableListOf()
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.fetchPackages()
//            packages.addAll(response.body()!!)
//        }
//        return packages
//    }
//
//
//    fun addPackage( pack: Package){
//        GlobalScope.launch(Dispatchers.IO) {
//            freelancerApi.addPackage(pack)
//        }
//    }
//
//
//   fun deletePackage(packageId: Long){
//       GlobalScope.launch(Dispatchers.IO) {
//           freelancerApi.deletePackage(packageId)
//       }
//   }
//
//    //Transfer
//    fun fetchTransfer(): List<Transfer>{
//        val transfers: MutableList<Transfer> = mutableListOf()
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.fetchTransfer()
//            transfers.addAll(response.body()!!)
//        }
//        return transfers
//    }
//
//
//    fun addTransfer(transfer: Transfer){
//        GlobalScope.launch(Dispatchers.IO) {
//            freelancerApi.addTransfer(transfer)
//        }
//    }
//
//    fun deleteTransfer(transferId: Long){
//        GlobalScope.launch(Dispatchers.IO) {
//            freelancerApi.deleteTransfer(transferId)
//        }
//    }
//
//
//    fun fetchTransferPackages( transferId: Long): List<Package>{
//        val packages: MutableList<Package> = mutableListOf()
//        GlobalScope.launch(Dispatchers.IO) {
//            val response = freelancerApi.fetchTransferPackages(transferId)
//            packages.addAll(response.body()!!)
//        }
//        return packages
//    }


}