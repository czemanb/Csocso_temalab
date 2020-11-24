package hu.bme.aut.freelancerandroid.repository.model

import com.google.gson.annotations.SerializedName

data class Vehicle(
    @SerializedName("cc")
    val cc: Int?,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("owner")
    val owner: Owner?,
    @SerializedName("weightLimit")
    val weightLimit: Double,
    @SerializedName("x")
    val x: Int,
    @SerializedName("y")
    val y: Int,
    @SerializedName("z")
    val z: Int
)