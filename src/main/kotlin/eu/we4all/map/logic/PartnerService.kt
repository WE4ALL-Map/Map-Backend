package eu.we4all.map.logic

import eu.we4all.map.data.City
import eu.we4all.map.logic.filters.Filter

interface PartnerService {
    fun getCityNames(): Map<String, String>

    fun getAllCities(): Map<String, City>

    fun getFilteredCities(filters: List<Filter>): Map<String, City>
}