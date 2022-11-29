package eu.we4all

import eu.we4all.map.data.City
import eu.we4all.map.logic.PartnerService
import eu.we4all.map.logic.filters.Filter

open class PartnerServiceMockBase : PartnerService {
    override fun getCityNames(): Map<String, String> {
        throw MissingMockException()
    }

    override fun getAllCities(): Map<String, City> {
        throw MissingMockException()
    }

    override fun getFilteredCities(filters: List<Filter>): Map<String, City> {
        throw MissingMockException()
    }
}