package hu.bme.aut.freelancerandroid.ui.user.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.fragments.ProfileFragment.Companion.TAG
import hu.bme.aut.freelancerandroid.repository.model.LoginData
import hu.bme.aut.freelancerandroid.repository.model.RegisterData
import hu.bme.aut.freelancerandroid.repository.model.User
import hu.bme.aut.freelancerandroid.repository.network.ServiceInterceptor
import hu.bme.aut.freelancerandroid.repository.response.LoginResponse
import hu.bme.aut.freelancerandroid.repository.repo.user.UserRepository
import hu.bme.aut.freelancerandroid.ui.pack.PackViewModel
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Response

class LoginViewModel(val userRepository: UserRepository): ViewModel() {

    lateinit var email: String
    lateinit var password: String
    val activeUser: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()



//    fun loginUser(email: String, password:String) = viewModelScope.launch {
//        activeUser.postValue(Resource.Loading())
//        val response = userRepository.loginUser(LoginData(email, password))
//        val code = response.code()
//        if (response.code() == 200) {
//            GlobalVariable.token = response.body()!!.token
//            Log.e(TAG, "An error occured: $response.body()!!.token")
//        }
//        else
//            GlobalVariable.token=("")
//
//        activeUser.postValue(handleLoginResponse(response))
//
//    }

    fun loginUser(email: String, password:String): String {
        lateinit var t :String
        runBlocking {
            val response = userRepository.loginUser(LoginData(email, password))
            if (response.code() == 200) {
                t = response.body()!!.token
               Log.e(TAG, "An error occured: $response.body()!!.token")

            }
            else{
                Log.e(TAG, "An error occured:")
            }
        }
        return t

    }

    fun addUser(user: RegisterData) {
        runBlocking {
            val response = userRepository.addUser(user)
            if (response.code() != 200) {
                Log.e(TAG, "Ezzel az e-mail címmel már regisztráltak!")
            }
        }
    }

    private fun handleLoginResponse(response: Response<LoginResponse>) : Resource<LoginResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }




}