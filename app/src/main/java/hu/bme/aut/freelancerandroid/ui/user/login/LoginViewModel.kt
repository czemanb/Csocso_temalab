package hu.bme.aut.freelancerandroid.ui.user.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import hu.bme.aut.freelancerandroid.repository.model.User

class LoginViewModel(savedStateHandle: SavedStateHandle): ViewModel() {
    val userId : String = savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")
    val user : LiveData<User> = TODO()
}