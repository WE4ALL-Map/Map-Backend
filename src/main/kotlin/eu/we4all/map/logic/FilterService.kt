package eu.we4all.map.logic

import eu.we4all.map.logic.filters.Filter
import io.ktor.server.request.*

interface FilterService {
    fun getFiltersFromRequest(request: ApplicationRequest): List<Filter>
}