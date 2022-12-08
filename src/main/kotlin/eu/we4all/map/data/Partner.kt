package eu.we4all.map.data

import eu.we4all.map.data.serializers.SerializableUUID
import kotlinx.serialization.Serializable

@Serializable
data class Partner(
    val id: SerializableUUID,
    val borough: SerializableUUID,
    val designing: Boolean,
    val printing: Boolean,
    val services: @Serializable List<String>,
    val tags: @Serializable List<String>,
)
