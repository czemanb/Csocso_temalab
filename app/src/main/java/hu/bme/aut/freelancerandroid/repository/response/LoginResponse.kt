package hu.bme.aut.freelancerandroid.repository.response

data class LoginResponse(
    val currentUserId: Long,
    val token: String,
    val expiresIn: Int
)