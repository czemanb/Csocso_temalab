package hu.bme.aut.freelancerandroid.network



import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val SERVICE_URL =
        "https://freelancer-carrier-spring.herokuapp.com/api/"

    val freelancerApi: FreelancerApi by lazy {
        retrofit.build().create(FreelancerApi::class.java)
    }


    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().baseUrl(SERVICE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(GsonConverterFactory.create())
    }

}