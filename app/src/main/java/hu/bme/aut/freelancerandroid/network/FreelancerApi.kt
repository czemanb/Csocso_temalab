package hu.bme.aut.freelancerandroid.network


import hu.bme.aut.freelancerandroid.model.Town
import hu.bme.aut.freelancerandroid.model.Transfer
import hu.bme.aut.freelancerandroid.model.User
import hu.bme.aut.freelancerandroid.model.Vehicle
import retrofit2.Call
import retrofit2.http.*

interface FreelancerApi {
    //USER
    @GET("/users")
    suspend fun fetchUsers(): Call<List<User>>

    @POST("/users")
    suspend fun addUser(@Body user: User): Call<User>

    @DELETE("/users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Long): Call<Void>

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId: Long): Call<User>

    @POST("/users/login")
    suspend fun loginUser(@Body user: User): Call<User>

    @GET("/users/myPackages")
    suspend fun fetchMyPackages(): Call<List<Package>>

    //Package
    @GET("/packages")
    suspend fun fetchPackages(): Call<List<Package>>

    @POST("/packages")
    suspend fun addPackage(@Body pack: Package): Call<Package>

    @DELETE("/package/{packageId}")
    suspend fun deletePackage(@Path("packageId") packageId: Long): Call<Void?>?

    @PUT("/packages/update_transfer/{packageId}")
    suspend fun updateTransfer(@Path ("packageId") @Body p: Package ): Call<Void>

    //Transfer
    @GET("/transfers")
    suspend fun fetchTransfer(): Call<List<Transfer>>

    @POST("/transfers")
    suspend fun addTransfer(@Body transfer: Transfer): Call<Transfer>

    @DELETE("/transfers/{transferId}")
    suspend fun deleteTransfer(@Path("transferId") TransferId: Long): Call<Void>

    @GET("/transfers/{transferId}/getPackages")
    suspend fun getTransferPackage(@Path("transferId") transferId: Long): Call<Transfer>

    @GET("/transfers/forCarrier/{carrierId}")
    suspend fun getTransfer(@Path("transferId") transferId: Long): Call<Transfer>

    //Vehicle
    @GET("/vehicles")
    suspend fun fetchVehicle(): Call<List<Vehicle>>

    @POST("/vehicles")
    suspend fun addVehicle(@Body vehicle: Vehicle): Call<Vehicle>

    @DELETE("/vehicles/{vehicleId}")
    suspend fun deleteVehicle(@Path("vehicleId") vehicleId: Long): Call<Void>

    @GET("/vehicles/forUser/{ownerId}")
    suspend fun getVehicles(@Path("ownerId") ownerId: Long): Call<List<Vehicle>>

    //Town
    @GET("/towns")
    suspend fun fetchTowns(): Call<List<Town>>

    @POST("/towns")
    suspend fun addTown(@Body town: Town): Call<Town>

    @DELETE("/towns/{townId}")
    suspend fun deleteTown(@Path("townId") townId: Long): Call<Void>

}