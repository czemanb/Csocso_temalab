package hu.bme.aut.freelancerandroid.ui.transfer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.repo.transfer.TransferRepository
import hu.bme.aut.freelancerandroid.repository.response.NavigationUrl
import hu.bme.aut.freelancerandroid.repository.response.TransferResponse
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class TransferViewModel(val transferRepository: TransferRepository): ViewModel(){

    val transfers: MutableLiveData<Resource<TransferResponse>> = MutableLiveData()
    val navigationUrl: MutableLiveData<Resource<NavigationUrl>> = MutableLiveData()

    init{
        fetchTransfer()
    }

    fun fetchTransfer()= viewModelScope.launch {
        transfers.postValue(Resource.Loading())
        val response = transferRepository.fetchTransfer("Bearer " + GlobalVariable.token)
        transfers.postValue(handleTranferResponse(response))
    }

    fun fetchTransferNavigationUrl(transferId: Long, originLat: Double, originLong: Double) =
        viewModelScope.launch {
            navigationUrl.postValue(Resource.Loading())
            Log.e("fetch", "asdf")
            val response = transferRepository.fetchTransferNavigationUrl(
                "Bearer " + GlobalVariable.token,
                transferId, originLat, originLong
            )
            Log.e("fetch", response.toString())
            navigationUrl.postValue(handleNavUrlResponse(response))
         }


    fun getVehiclesTransfer(id: Long)= viewModelScope.launch {
        transfers.postValue(Resource.Loading())
        val response = transferRepository.getVehicles(id)
        transfers.postValue(handleTranferResponse(response))
    }

    fun addTransfer(transfer: Transfer) = viewModelScope.launch {

    }

    fun deleteTransfer(transferId: Long) = viewModelScope.launch {

    }



    private fun handleTranferResponse(response: Response<TransferResponse>) : Resource<TransferResponse>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleNavUrlResponse(response: Response<NavigationUrl>): Resource<NavigationUrl>? {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}
