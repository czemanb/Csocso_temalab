package hu.bme.aut.freelancerandroid.ui.pack

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.repository.model.Package
import hu.bme.aut.freelancerandroid.repository.repo.pack.PackRepository
import hu.bme.aut.freelancerandroid.repository.response.LoginResponse
import hu.bme.aut.freelancerandroid.repository.response.PackResponse
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PackViewModel(val packRepository: PackRepository):ViewModel() {

    val packs: MutableLiveData<Resource<PackResponse>> = MutableLiveData()


    init {
        //fetchPackages()
        fetchUserPackages()
    }


     fun fetchPackages() = viewModelScope.launch {
         packs.postValue(Resource.Loading())
         val response = packRepository.fetchPackages("Bearer " + GlobalVariable.token)
         packs.postValue(handlePackResponse(response))
     }

    fun fetchUserPackages() = viewModelScope.launch {
        packs.postValue(Resource.Loading())
        val response = packRepository.fetchUserPackages("Bearer " + GlobalVariable.token,1) //Todo ActiveUserId
        packs.postValue(handlePackResponse(response))
    }


     fun addPackage(pack: Package) = viewModelScope.launch {
         val response = packRepository.addPackage("Bearer " + GlobalVariable.token, pack)
     }


    fun deletePackage(packageId: Long) = viewModelScope.launch {

    }



    private fun handlePackResponse(response: Response<PackResponse>) : Resource<PackResponse>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}