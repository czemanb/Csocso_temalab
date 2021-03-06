package hu.bme.aut.freelancerandroid.network


import hu.bme.aut.freelancerandroid.repository.dto.PackDto
import hu.bme.aut.freelancerandroid.repository.dto.TransferDto
import hu.bme.aut.freelancerandroid.repository.dto.VehicleDto
import hu.bme.aut.freelancerandroid.repository.model.*
import hu.bme.aut.freelancerandroid.repository.response.*
import retrofit2.Response
import retrofit2.http.*

interface FreelancerApi {

    @POST("users")
    suspend fun addUser(@Body user: RegisterData): Response<Long>

    @GET("users/{userId}")
    suspend fun getUser(@Header("Authorization")authHeader:String?,@Path("userId") userId: Long): Response<UserResponse>

    @POST("users/login")
   suspend fun loginUser(@Body loginData: LoginData) : Response<LoginResponse>

    @GET("users/packages/{userId}")
    suspend fun fetchUserPackages(@Header("Authorization")authHeader:String?, @Path("userId") userId: Long): Response<PackResponse>

    @GET("users/transfers/{userId}")
    suspend fun fetchUserTransfer(@Header("Authorization")authHeader:String?, @Path("userId") userId: Long): Response<TransferResponse>

    @GET("users/vehicles/{userId}")
    suspend fun fetchUserVehicles(@Header("Authorization")authHeader:String?, @Path("userId") userId: Long): Response<VehicleResponse>

    //Package
    @GET("packages")
    suspend fun fetchPackages(@Header("Authorization")authHeader:String?): Response<PackResponse>

    @POST("packages")
    suspend fun addPackage(@Header("Authorization")authHeader:String?, @Body pack: PackDto): Response<Long>

    @DELETE("packages/{packageId}")
    suspend fun deletePackage(@Header("Authorization")authHeader:String?, @Path("packageId") packageId: Long): Response<Void>

   @PUT("packages/changeStatus/{packageId}")
   suspend fun changeStatusPackage(@Header("Authorization")authHeader:String?, @Path("packageId")packageId: Long, @Query("status") status: String)

    //Transfer
    @GET("transfers")
    suspend fun fetchTransfer(@Header("Authorization")authHeader:String?): Response<TransferResponse>

    @GET("transfers/navigationUrl/{transferId}")
    suspend fun fetchTransferNavigationUrl(
      @Header("Authorization")authHeader:String?,
      @Path("transferId") transferId: Long,
      @Query("originLat") originLat: Double,
      @Query("originLong") originLong: Double
    ): Response<NavigationUrl>

    @POST("transfers")
    suspend fun addTransfer(@Header("Authorization")authHeader:String?, @Body transfer: TransferDto): Response<Long>

    @DELETE("transfers/{transferId}")
    suspend fun deleteTransfer(@Header("Authorization")authHeader:String?, @Path("transferId") transferId: Long): Response<Void>

    @GET("transfers/packages/{transferId}")
    suspend fun fetchTransferPackages(@Header("Authorization")authHeader:String?, @Path("transferId") transferId: Long): Response<PackResponse>


    //Vehicle
    @GET("vehicles")
    suspend fun fetchVehicle(@Header("Authorization")authHeader:String?): Response<VehicleResponse>

    @POST("vehicles")
    suspend fun addVehicle(@Header("Authorization")authHeader:String?, @Body vehicle: VehicleDto): Response<Long>

    @DELETE("vehicles/{vehicleId}")
    suspend fun deleteVehicle(@Header("Authorization")authHeader:String?, @Path("vehicleId") vehicleId: Long): Response<Void>

    @GET("vehicles/transfers/{vehicleId}")
    suspend fun getVehicles(@Path("vehicleId") vehicleId: Long): Response<TransferResponse>



}