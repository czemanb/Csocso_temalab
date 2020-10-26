package hu.bme.aut.freelancerandroid.model

import java.util.*

data class Transfer(
    val id: Long,
    val date: Date,
    val townId: Long,
    val vehicleId: Long,
    val carriedId: Long
)