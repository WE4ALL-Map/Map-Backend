package eu.we4all.map.logic

import eu.we4all.map.data.City

interface PartnerService {
    fun getAllCities(): Map<String, City>

    fun getFilteredCities(filters: List<Filter>): Map<String, City>
}