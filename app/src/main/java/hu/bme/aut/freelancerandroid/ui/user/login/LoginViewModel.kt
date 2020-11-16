package hu.bme.aut.freelancerandroid.ui.user.login

import android.view.View
import androidx.lifecycle.*
import hu.bme.aut.freelancerandroid.repository.model.LoginResponse
import hu.bme.aut.freelancerandroid.repository.model.User
import hu.bme.aut.freelancerandroid.repository.repo.user.UserRepository
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(val userRepository: UserRepository): ViewModel() {

    lateinit var email: String
    lateinit var password: String
    val activeUser: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()



    fun loginUser(email: String, password:String) = viewModelScope.launch {
        activeUser.postValue(Resource.Loading())
        val response = userRepository.loginUser(email, password)
        activeUser.postValue(handleLoginResponse(response))
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