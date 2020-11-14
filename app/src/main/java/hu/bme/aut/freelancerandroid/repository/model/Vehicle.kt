package hu.bme.aut.freelancerandroid.repository.model

data class Vehicle(
    val id: Long,
    var x: Int,
    var y: Int,
    var z: Int,
    var weightLimit: Int,
    val owner: User
    //var ownerId: Long
)