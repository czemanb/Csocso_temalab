package hu.bme.aut.freelancerandroid.repository.network


import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto
import hu.bme.aut.freelancerandroid.repository.model.*
import hu.bme.aut.freelancerandroid.repository.response.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface FreelancerApi {
    //USER
    @GET("users")
    suspend fun fetchUsers(): Response<List<User>>

    @POST("users")
    suspend fun addUser(@Body user: RegisterData): Response<Long>

    @DELETE("users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: Long): Call<Void>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Long): Response<User>

    @POST("users/login")
   suspend fun loginUser(@Body loginData: LoginData) : Response<LoginResponse>

    @GET("users/packages/{userId}")
    suspend fun fetchUserPackages(@Header("Authorization")authHeader:String?, @Path("userId") userId: Long): Response<PackResponse>

    @GET("users/transfer/{userId}")
    suspend fun fetchUserTransfer(@Header("Authorization")authHeader:String?, @Path("userId") userId: Long): Response<TransferResponse>

    @GET("users/vehicles/{userId}")
    suspend fun fetchUserVehicles(@Header("Authorization")authHeader:String?, @Path("userId") userId: Long): Response<VehicleResponse>

    //Package
    @GET("packages")
    suspend fun fetchPackages(@Header("Authorization")authHeader:String?): Response<PackResponse>

    @POST("packages")
    suspend fun addPackage(@Header("Authorization")authHeader:String?, @Body pack: Package): Response<Long>

    @DELETE("package/{packageId}")
    suspend fun deletePackage(@Header("Authorization")authHeader:String?, @Path("packageId") packageId: Long): Call<Void>

    //Transfer
    @GET("transfers")
    suspend fun fetchTransfer(@Header("Authorization")authHeader:String?): Response<TransferResponse>

    @FormUrlEncoded
    @POST("transfers")
    suspend fun addTransfer(@Header("Authorization")authHeader:String?, @Body transfer: Transfer): Response<Long>

    @DELETE("transfers/{transferId}")
    suspend fun deleteTransfer(@Header("Authorization")authHeader:String?, @Path("transferId") transferId: Long): Call<Void>

    @GET("transfers/packages/{transferId}")
    suspend fun fetchTransferPackages(@Header("Authorization")authHeader:String?, @Path("transferId") transferId: Long): Response<PackResponse>


    //Vehicle
    @GET("vehicles")
    suspend fun fetchVehicle(@Header("Authorization")authHeader:String?): Response<VehicleResponse>

    @POST("vehicles")
    suspend fun addVehicle(@Header("Authorization")authHeader:String?, @Body vehicle: VehicleDto): Response<Long>

    @DELETE("vehicles/{vehicleId}")
    suspend fun deleteVehicle(@Header("Authorization")authHeader:String?, @Path("vehicleId") vehicleId: Long): Call<Void>

    @GET("vehicles/transfers/{vehicleId}")
    suspend fun getVehicles(@Path("vehicleId") vehicleId: Long): Response<TransferResponse>

    //Town
    @GET("towns")
    suspend fun fetchTowns(): Response<List<Town>>

    @FormUrlEncoded
    @POST("towns")
    suspend fun addTown(@Body town: Town): Response<Long>

    @DELETE("towns/{townId}")
    suspend fun deleteTown(@Path("townId") townId: Long): Call<Void>

    @GET("towns/packages/{townId}")
    suspend fun getPackagesInTown(@Path("townId") townId: Long): Response<PackResponse>

    @GET("towns/transfers/{townId")
    suspend fun getTransfersInTown(@Path("townId") townId: Long): Response<TransferResponse>

}