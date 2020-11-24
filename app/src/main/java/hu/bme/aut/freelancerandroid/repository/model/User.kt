package hu.bme.aut.freelancerandroid.repository.model

data class User (
    val id: Long,
    var name: String,
    var email: String,
    var phonenumber: String,
    var password: String,
    var passwordConfirm: String,
    var hasInsurence: Boolean
)