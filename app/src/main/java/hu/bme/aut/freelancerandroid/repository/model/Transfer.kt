package hu.bme.aut.freelancerandroid.repository.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class Transfer(
    @SerializedName("carrier")
val carrier: Carrier?,
@SerializedName("createdAt")
val createdAt: Date?,
@SerializedName("date")
val date: String?,
@SerializedName("id")
val id: Long?,
@SerializedName("town")
val town: Town?,
@SerializedName("vehicle")
val vehicle: Vehicle?

)