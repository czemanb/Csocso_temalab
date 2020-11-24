package hu.bme.aut.freelancerandroid.ui.transfer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hu.bme.aut.freelancerandroid.repository.model.Transfer
import hu.bme.aut.freelancerandroid.repository.repo.transfer.TransferRepository
import hu.bme.aut.freelancerandroid.repository.response.TransferResponse
import hu.bme.aut.freelancerandroid.util.GlobalVariable
import hu.bme.aut.freelancerandroid.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class TransferViewModel(val transferRepository: TransferRepository): ViewModel(){

    val transfers: MutableLiveData<Resource<TransferResponse>> = MutableLiveData()

    init{
        fetchTransfer()
    }

    fun fetchTransfer()= viewModelScope.launch {
        transfers.postValue(Resource.Loading())
        val response = transferRepository.fetchTransfer("Bearer " + GlobalVariable.token)
        transfers.postValue(handleTranferResponse(response))
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



}