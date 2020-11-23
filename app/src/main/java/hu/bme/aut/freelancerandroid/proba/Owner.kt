package hu.bme.aut.freelancerandroid.proba


import com.google.gson.annotations.SerializedName

data class Owner(
    @SerializedName("email")
    val email: String,
    @SerializedName("hasInsurance")
    val hasInsurance: Boolean,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phonenumber")
    val phonenumber: String
)