package eu.we4all.map.logic.impl

import eu.we4all.map.logic.filters.Filter
import eu.we4all.map.logic.FilterService
import eu.we4all.map.logic.exceptions.UnknownFilterException
import eu.we4all.map.logic.filters.CityNameFilter
import eu.we4all.map.util.map
import io.ktor.server.request.*
import io.ktor.util.*

class FilterServiceImpl : FilterService {
    override fun getFiltersFromRequest(request: ApplicationRequest): List<Filter> =
        request.queryParameters.toMap().map(this::getFilterForNameAndParameters)

    private fun getFilterForNameAndParameters(name: String, parameters: List<String>): Filter {
        return when (name) {
            "cityName" -> CityNameFilter(parameters)
            else -> throw UnknownFilterException(name)
        }
    }
}