package hu.bme.aut.freelancerandroid.repository.dto

data class PackDto (
    val name: String,
    val size: String,
    val weight: Double,
    val fromLat: Double,
    val toLat: Double,
    val fromLong: Double,
    val toLong: Double,
    val senderId:Long,
    val dateLimit: String,
    val townId: Long,
    val value: Int
)
