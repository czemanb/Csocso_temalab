package hu.bme.aut.freelancerandroid.repository.response

data class LoginResponse(
    val token: String,
    val expiresIn: Int
)