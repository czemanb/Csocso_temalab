package hu.bme.aut.freelancerandroid.repository.dto


import hu.bme.aut.freelancerandroid.repository.model.Vehicle

data class TransferDto(
    val date: String?,
    val townId: Long,
    val vehicleId: Vehicle,
    val carrierId: Long,
    val fromLat: Double,
    val fromLong: Double,
    val toLat: Double,
    val toLong: Double,
    val startTime: Int
)