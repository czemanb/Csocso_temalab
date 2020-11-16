package hu.bme.aut.freelancerandroid.repository.network


import hu.bme.aut.freelancerandroid.repository.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import java.net.ResponseCache

interface FreelancerApi {
    //USER
    @GET("users")
    suspend fun fetchUsers(): Response<List<User>>

    @FormUrlEncoded
    @POST("users")
    suspend fun addUser(@Body user: User): Call<User>

    @DELETE("users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Long): Call<Void>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Long): Response<User>

    @FormUrlEncoded
    @POST("users/login")
    suspend fun loginUser(@Field ("email") email: String, @Field ("password") password: String): Response<LoginResponse>

    @GET("users/packages/{userId}")
    suspend fun fetchUserPackages(@Path("userId") userId: Long): Response<List<Package>>

    @GET("users/transfer/{userId}")
    suspend fun fetchUserTransfer(@Path("userId") userId: Long): Response<List<Transfer>>

    @GET("users/vehicles/{userId}")
    suspend fun fetchUserVehicles(@Path("userId") userId: Long): Response<List<Vehicle>>

    //Package
    @GET("packages")
    suspend fun fetchPackages(): Response<List<Package>>


    @POST("packages")
    suspend fun addPackage(@Body pack: Package): Call<Package>

    @DELETE("package/{packageId}")
    suspend fun deletePackage(@Path("packageId") packageId: Long): Call<Void>

    //Transfer
    @GET("transfers")
    suspend fun fetchTransfer(): Response<List<Transfer>>

    @POST("transfers")
    suspend fun addTransfer(@Body transfer: Transfer): Call<Transfer>

    @DELETE("transfers/{transferId}")
    suspend fun deleteTransfer(@Path("transferId") transferId: Long): Call<Void>

    @GET("transfers/packages/{transferId}")
    suspend fun fetchTransferPackages(@Path("transferId") transferId: Long): Response<List<Package>>


    //Vehicle
    @GET("vehicles")
    suspend fun fetchVehicle(): Response<List<Vehicle>>

    @POST("vehicles")
    suspend fun addVehicle(@Body vehicle: Vehicle): Call<Vehicle>

    @DELETE("vehicles/{vehicleId}")
    suspend fun deleteVehicle(@Path("vehicleId") vehicleId: Long): Call<Void>

    @GET("vehicles/transfers/{vehicleId}")
    suspend fun getVehicles(@Path("vehicleId") vehicleId: Long): Response<List<Transfer>>

    //Town
    @GET("towns")
    suspend fun fetchTowns(): Response<List<Town>>

    @POST("towns")
    suspend fun addTown(@Body town: Town): Call<Town>

    @DELETE("towns/{townId}")
    suspend fun deleteTown(@Path("townId") townId: Long): Call<Void>

    @GET("towns/packages/{townId}")
    suspend fun getPackagesInTown(@Path("townId") townId: Long): Response<List<Package>>

    @GET("towns/transfers/{townId")
    suspend fun getTransfersInTown(@Path("townId") townId: Long): Response<List<Transfer>>

}