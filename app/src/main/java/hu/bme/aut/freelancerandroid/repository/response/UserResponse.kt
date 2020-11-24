package hu.bme.aut.freelancerandroid.repository.response

data class UserResponse(
    val id: Long,
    var name: String,
    var email: String,
    var phonenumber: String,
    var hasInsurance: Boolean
)
