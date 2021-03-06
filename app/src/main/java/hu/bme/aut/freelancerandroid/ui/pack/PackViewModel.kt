package hu.bme.aut.freelancerandroid.ui.pack

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.repository.dto.PackDto
import hu.bme.aut.freelancerandroid.repository.repo.pack.PackRepository
import hu.bme.aut.freelancerandroid.repository.response.PackResponse
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class PackViewModel(private val packRepository: PackRepository):ViewModel() {

    val packs: MutableLiveData<Resource<PackResponse>> = MutableLiveData()
    val transferPacks: MutableLiveData<Resource<PackResponse>> = MutableLiveData()


    init {
        fetchUserPackages()
    }


     private fun fetchPackages() = viewModelScope.launch {
         packs.postValue(Resource.Loading())
         val response = packRepository.fetchPackages("Bearer " + GlobalVariable.token)
         packs.postValue(handlePackResponse(response))
     }

    private fun fetchUserPackages() = viewModelScope.launch {
        packs.postValue(Resource.Loading())
        val response = packRepository.fetchUserPackages("Bearer " + GlobalVariable.token,1) //Todo ActiveUserId
        packs.postValue(handlePackResponse(response))
    }

    fun fetchTransferPackages(transferId : Long) = viewModelScope.launch {
        transferPacks.postValue(Resource.Loading())
        val response = packRepository.fetchTransferPackages("Bearer " + GlobalVariable.token,transferId)
        transferPacks.postValue(handlePackResponse(response))
    }

    fun changePackageStatus(packageId: Long, status: String) = viewModelScope.launch{
        packRepository.changeStatusPackage("Bearer " + GlobalVariable.token,packageId,status)
    }


     fun addPackage(pack: PackDto) = viewModelScope.launch {
         val response = packRepository.addPackage("Bearer " + GlobalVariable.token, pack)
         if (response.code() ==200){
             fetchUserPackages()
         }
         else{
             Log.e("eeee","Hiba van")
         }

     }

    fun deletePackage(packageId: Long) = viewModelScope.launch {
        val response =packRepository.deletePackage("Bearer " + GlobalVariable.token, packageId)
        fetchUserPackages()
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