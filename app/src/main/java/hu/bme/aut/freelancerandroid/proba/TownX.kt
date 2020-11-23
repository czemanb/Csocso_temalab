package hu.bme.aut.freelancerandroid.proba


import com.google.gson.annotations.SerializedName

data class TownX(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)