package hu.bme.aut.freelancerandroid.ui.transfer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.freelancerandroid.repository.repo.transfer.TransferRepository


class TransferViewModelProviderFactory(private val transferRepository: TransferRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TransferViewModel(transferRepository) as T
    }
}