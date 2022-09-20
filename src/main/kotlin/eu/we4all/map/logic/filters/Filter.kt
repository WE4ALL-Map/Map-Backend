package eu.we4all.map.logic.filters

import eu.we4all.map.data.City

interface Filter {
    fun apply(cities: Map<String, City>): Map<String, City>
}