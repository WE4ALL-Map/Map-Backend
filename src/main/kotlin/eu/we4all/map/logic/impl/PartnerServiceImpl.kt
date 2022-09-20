package eu.we4all.map.logic.impl

import eu.we4all.map.data.City
import eu.we4all.map.logic.filters.Filter
import eu.we4all.map.logic.PartnerService
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.File

@OptIn(ExperimentalSerializationApi::class)
class PartnerServiceImpl constructor(path: String) : PartnerService {
    private val cities: Map<String, City>

    init {
        val stream = File(path).inputStream()

        cities = Json.decodeFromStream(stream)
    }

    override fun getAllCities(): Map<String, City> {
        return cities
    }

    override fun getFilteredCities(filters: List<Filter>): Map<String, City> {
        var filteredCities = cities

        for (filter in filters) {
            filteredCities = filter.apply(filteredCities)
        }

        return filteredCities
    }
}