package hu.bme.aut.freelancerandroid.proba


import com.google.gson.annotations.SerializedName
import java.util.*

data class pack1Item(
    @SerializedName("createdAt")
    val createdAt: Date,
    @SerializedName("dateLimit")
    val dateLimit: Date,
    @SerializedName("fromLat")
    val fromLat: Double,
    @SerializedName("fromLong")
    val fromLong: Double,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("sender")
    val sender: Sender,
    @SerializedName("size")
    val size: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("toLat")
    val toLat: Double,
    @SerializedName("toLong")
    val toLong: Double,
    @SerializedName("town")
    val town: Town,
    @SerializedName("transfer")
    val transfer: Transfer,
    @SerializedName("value")
    val value: Int,
    @SerializedName("weight")
    val weight: Double
)