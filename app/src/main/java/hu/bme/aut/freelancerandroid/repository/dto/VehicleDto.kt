package hu.bme.aut.freelancerandroid.repository.dto

data class VehicleDto(
    val  name: String,
    val x: Int,
    val y : Int ,
    val z: Int,
    val weightLimit: Double,
    val ownerId : Long
)
