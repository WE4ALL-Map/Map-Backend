package eu.we4all

import eu.we4all.map.data.City
import eu.we4all.map.logic.PartnerService

open class PartnerServiceMockBase : PartnerService {
    override fun getAllCities(): Map<String, City> {
        throw MissingMockException()
    }
}