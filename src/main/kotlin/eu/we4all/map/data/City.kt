package eu.we4all.map.data

import eu.we4all.map.data.serializers.SerializableUUID
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class City(
    val id: SerializableUUID,
    @SerialName("display_name") val displayName: String,
    val center: Coordinates,
    val boroughs: @Serializable List<Borough>,
    val partners: @Serializable List<Partner>,
) {
    fun toPartialCity() =
        PartialCity(
            this.id,
            this.displayName,
            this.center,
            this.boroughs,
            this.partners.size
        )
}
