package hu.bme.aut.freelancerandroid.repository.repo.user


import hu.bme.aut.freelancerandroid.repository.model.*
import hu.bme.aut.freelancerandroid.network.NetworkManager


class UserRepository{
    suspend fun addUser(user: RegisterData) = NetworkManager.freelancerApi.addUser(user)

    suspend fun loginUser(loginData: LoginData) = NetworkManager.freelancerApi.loginUser(loginData)

    suspend fun getUser(authHeader: String?, userId: Long) = NetworkManager.freelancerApi.getUser(authHeader, userId)

}