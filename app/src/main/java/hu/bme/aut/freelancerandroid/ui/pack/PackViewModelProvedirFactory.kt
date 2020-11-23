package hu.bme.aut.freelancerandroid.ui.pack

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.freelancerandroid.repository.repo.pack.PackRepository

class PackViewModelProvedirFactory(private val packRepository: PackRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PackViewModel(packRepository) as T
    }
}