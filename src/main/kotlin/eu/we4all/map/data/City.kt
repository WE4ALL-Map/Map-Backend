package eu.we4all.map.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    val fullName: String,
    @SerialName("lat") val latitude: Float,
    @SerialName("long") val longitude: Float,
    val partners: Int
)