package hu.bme.aut.freelancerandroid.model

data class User (
    val id: Long,
    var name: String,
    var email: String,
    var phonenumber: String,
    var password: String,
    var password_confirm: String
)