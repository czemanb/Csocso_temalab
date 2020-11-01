package hu.bme.aut.freelancerandroid.model

data class Package(
    val id: Long,
    var name: String,
    var size: String,
    var weight: Double,
    var from_lat: Double,
    var to_lat: Double,
    var from_long: Double,
    var to_long: Double,
    var status: Status,
    var user: User,
    var transfer: Transfer?
    //val senderId: Int,
    //val transferId: Int?


)

enum class Status{
    WAITING, INCAR, DELIVERED
}

enum class Size{
    S, M, L, XL
}

