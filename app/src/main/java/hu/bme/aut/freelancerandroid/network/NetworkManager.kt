package hu.bme.aut.freelancerandroid.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private const val SERVICE_URL = "localhost:8080/api"

    private val freelancerApi: FreelancerApi

    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        freelancerApi = retrofit.create(FreelancerApi::class.java)
    }
}