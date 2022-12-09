package eu.we4all.map.logic

import eu.we4all.map.data.City
import eu.we4all.map.data.PartialCity
import eu.we4all.map.data.serializers.SerializableUUID
import kotlinx.serialization.Serializable

interface PartnerService {
    fun getCityNames(): @Serializable Map<SerializableUUID, String>

    fun getCities(): List<PartialCity>

    fun getCity(id: SerializableUUID): City?
}
