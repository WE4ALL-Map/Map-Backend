package eu.we4all.map.data

import eu.we4all.map.data.serializers.SerializableUUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DetailedBorough(
    val id: SerializableUUID,
    @SerialName("display_name") val displayName: String,
    val center: Coordinates,
    val partners: @Serializable List<Partner>,
)
