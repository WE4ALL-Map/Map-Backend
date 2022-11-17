package eu.we4all.map.logic

import eu.we4all.map.data.City

interface PartnerService {
    fun getCityNames(): Map<String, String>

    fun getAllCities(): Map<String, City>
}