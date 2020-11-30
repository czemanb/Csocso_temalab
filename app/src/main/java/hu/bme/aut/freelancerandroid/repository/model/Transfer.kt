package hu.bme.aut.freelancerandroid.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Transfer(
    @SerializedName("carrier")
    val carrier: Carrier?,
    @SerializedName("createdAt")
    val createdAt: Date?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("id")
    val id: Long,
    @SerializedName("town")
    val town: Town?,
    @SerializedName("vehicle")
    val vehicle: Vehicle?,
    @SerializedName("fromLat")
    val fromLat: Double,
    @SerializedName("fromLong")
    val fromLong: Double,
    @SerializedName("toLat")
    val toLat: Double,
    @SerializedName("toLong")
    val toLong: Double,
    @SerializedName("encodedRoute")
    var encodedRoute: String?,
    @SerializedName("startTime")
    val startTime: String?
) : Serializable