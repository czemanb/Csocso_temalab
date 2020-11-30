package hu.bme.aut.freelancerandroid.repository.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Package(
    @SerializedName("createdAt")
val createdAt: String,
    @SerializedName("dateLimit")
val dateLimit: String,
    @SerializedName("fromLat")
val fromLat: Double,
    @SerializedName("fromLong")
val fromLong: Double,
@SerializedName("id")
val id: Long,
@SerializedName("name")
val name: String,
    @SerializedName("sender")
val sender: Sender,
    @SerializedName("size")
val size: String,
@SerializedName("status")
var status: String,
@SerializedName("toLat")
val toLat: Double,
    @SerializedName("toLong")
val toLong: Double,
    @SerializedName("town")
val town: Town,
    @SerializedName("transfer")
val transfer: Transfer?,
    @SerializedName("value")
val value: Int,
    @SerializedName("weight")
val weight: Double,
    @SerializedName("arriveTime")
val deliveryTime: String?,
    @SerializedName("pickupTime")
val pickupTime: String?
) :Serializable