package hu.bme.aut.freelancerandroid.ui.user


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.repository.model.LoginData
import hu.bme.aut.freelancerandroid.repository.model.RegisterData
import hu.bme.aut.freelancerandroid.repository.response.LoginResponse
import hu.bme.aut.freelancerandroid.repository.repo.user.UserRepository
import hu.bme.aut.freelancerandroid.repository.response.UserResponse
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val userRepository: UserRepository): ViewModel() {


    val activeUser: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    val activeUserData: MutableLiveData<Resource<UserResponse>> = MutableLiveData()
    val newUser : MutableLiveData<Resource<Long>> = MutableLiveData()

    fun loginUser(email: String, password:String) = viewModelScope.launch {
        activeUser.postValue(Resource.Loading())
        val response = userRepository.loginUser(LoginData(email, password))
        activeUser.postValue(handleLoginResponse(response))

    }

    fun addUser(user: RegisterData)= viewModelScope.launch {
            newUser.postValue(Resource.Loading())
            val response = userRepository.addUser(user)
             newUser.postValue(handleRegistersponse(response))

    }

    fun getUser() = viewModelScope.launch {
        activeUserData.postValue(Resource.Loading())
        val response = userRepository.getUser("Bearer " + GlobalVariable.token,GlobalVariable.activeUser)
        activeUserData.postValue(handleUserResponse(response))
    }

    private fun handleLoginResponse(response: Response<LoginResponse>) : Resource<LoginResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleRegistersponse(response: Response<Long>) : Resource<Long> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleUserResponse(response: Response<UserResponse>) : Resource<UserResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}