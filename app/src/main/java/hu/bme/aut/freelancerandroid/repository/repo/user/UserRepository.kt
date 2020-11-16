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


    suspend fun addUser(user: User){
        NetworkManager.freelancerApi.addUser(user)
    }


    suspend fun deleteUser(userId: Long) {
        NetworkManager.freelancerApi.deleteUser(userId)
    }

//     fun getUser(userId: Long): LiveData<LoginResponse> {
//        job = Job()
//        return object: LiveData<LoginResponse>(){
//            override fun onActive() {
//                super.onActive()
//                job?.let{ theJob ->
//                    CoroutineScope(IO + theJob).launch {
//                        val response = NetworkManager.freelancerApi.getUser(userId)
//                        if (response.code()==200){
//                        withContext(Main){
//                            val user = (response.body()!!)
//                            value = user
//                            theJob.complete()
//                        }
//                    }
//                    }
//                }
//
//            }
//        }
//    }

//        lateinit var user : User
//        val response = NetworkManager.freelancerApi.getUser(userId)
//        if (response.code()==200)
//            user = (response.body()!!)
//        return user
//    }
//
//    fun cancelJobs(){
//        job?.cancel()
//    }


//   fun loginUser(email:String, password: String): LiveData<String>{
//        val loginResponse = MutableLiveData<String>()
//       GlobalScope.launch(Dispatchers.IO) {
//           val response= NetworkManager.freelancerApi.loginUser(email, password)
//           loginResponse.value= response.toString()
//       }
//       return loginResponse
//   }

    suspend fun loginUser(email:String, password: String) = NetworkManager.freelancerApi.loginUser(email, password)


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