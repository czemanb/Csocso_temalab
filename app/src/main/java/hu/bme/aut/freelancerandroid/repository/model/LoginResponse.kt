package hu.bme.aut.freelancerandroid.repository.model

data class LoginResponse(
    val token: String,
    val expiresIn: Int
)