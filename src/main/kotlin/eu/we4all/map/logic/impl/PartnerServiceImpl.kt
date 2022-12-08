package eu.we4all.map.logic.impl

import eu.we4all.map.data.City
import eu.we4all.map.data.PartialCity
import eu.we4all.map.data.serializers.SerializableUUID
import eu.we4all.map.logic.PartnerService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
class PartnerServiceImpl constructor(path: String) : PartnerService {
    private val cities: List<City>

    init {
        val stream = File(path).inputStream()

        cities = Json.decodeFromStream(stream)
    }

    override fun getCityNames(): Map<SerializableUUID, String> =
        buildMap { cities.forEach { put(it.id, it.displayName) } }

    override fun getCities(): List<PartialCity> =
        cities.map { it.toPartialCity() }

    override fun getCity(id: SerializableUUID): City? =
        cities.find { it.id == id }
}
