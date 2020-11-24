package hu.bme.aut.freelancerandroid.proba


import com.google.gson.annotations.SerializedName

data class Town(
    @SerializedName("id")
val id: Long,
@SerializedName("name")
val name: String
)