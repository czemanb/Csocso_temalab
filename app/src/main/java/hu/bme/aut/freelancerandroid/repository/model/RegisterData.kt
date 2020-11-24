package hu.bme.aut.freelancerandroid.repository.model

data class RegisterData (
    var name: String,
    var email: String,
    var phonenumber: String,
    var password: String,
    var passwordConfirm: String,
    var hasInsurance: Boolean
)