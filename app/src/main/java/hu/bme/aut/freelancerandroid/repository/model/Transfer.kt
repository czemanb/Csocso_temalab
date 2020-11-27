package hu.bme.aut.freelancerandroid.repository.model

import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import hu.bme.aut.freelancerandroid.repository.converter.TimeConverter
import org.threeten.bp.LocalTime
import java.sql.Time
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
    val vehicle: Vehicle?,
    @SerializedName("fromLat")
    val fromLat: Double,
    @SerializedName("fromLong")
    val fromLong: Double,
    @SerializedName("toLat")
    val toLat: Double,
    @SerializedName("toLong")
    val toLong: Double,
    @JsonAdapter(TimeConverter::class)
    val startTime: LocalTime?,
    @SerializedName("encodedRoute")
    val encodedRoute: String?
)