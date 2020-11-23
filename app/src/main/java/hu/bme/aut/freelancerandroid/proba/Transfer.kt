package hu.bme.aut.freelancerandroid.proba


import com.google.gson.annotations.SerializedName

data class Transfer(
    @SerializedName("carrier")
    val carrier: Carrier,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("town")
    val town: Town,
    @SerializedName("vehicle")
    val vehicle: Vehicle
)