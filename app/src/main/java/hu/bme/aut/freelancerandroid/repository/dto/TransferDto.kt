package hu.bme.aut.freelancerandroid.repository.dto


data class TransferDto(
    val date: String?,
    val townId: Long,
    val vehicleId: Long,
    val carrierId: Long,
    val fromLat: Double,
    val fromLong: Double,
    val toLat: Double,
    val toLong: Double,
    val startTime: String
)