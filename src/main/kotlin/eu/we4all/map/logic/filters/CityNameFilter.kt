package eu.we4all.map.logic.filters

import eu.we4all.map.data.City

class CityNameFilter(
    parameters: List<String>,
) : Filter {
    private val cityName: String

    init {
        if (parameters.size != 1) {
            throw IllegalArgumentException("The city name filter only takes one argument.")
        }

        this.cityName = parameters[0]
    }

    override fun apply(cities: Map<String, City>): Map<String, City> {
        return cities.filter { (nameFromMap, _) -> nameFromMap.lowercase() == cityName.lowercase() }
    }
}