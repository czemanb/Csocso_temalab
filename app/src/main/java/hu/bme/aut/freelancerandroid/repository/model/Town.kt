package hu.bme.aut.freelancerandroid.repository.model

import com.google.gson.annotations.SerializedName

class Town (
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)


