package hu.bme.aut.freelancerandroid.model

data class Vehicle(
    val id: Long,
    var x: Int,
    var y: Int,
    var z: Int,
    var weightLimit: Int,
    var ownerId: Long
)