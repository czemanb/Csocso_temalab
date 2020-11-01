package hu.bme.aut.freelancerandroid.network


import hu.bme.aut.freelancerandroid.model.User
import retrofit2.Call
import retrofit2.http.GET

interface FreelancerApi {
    @GET("/users")
    fun getUsers(): Call<List<User>>

}