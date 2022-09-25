package eu.we4all.map.routes

import eu.we4all.map.logic.FilterService
import eu.we4all.map.logic.PartnerService
import eu.we4all.map.logic.exceptions.UnknownFilterException
import eu.we4all.map.logic.filters.Filter
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.cities(partnerService: PartnerService, filterService: FilterService) {
    get {
        val cities = partnerService.getAllCities()
        call.respond(cities)
    }

    route("/names") {
        get {
            val cityNames = partnerService.getCityNames()
            call.respond(cityNames)
        }
    }

    route("/filtered") {
        get {
            val filters: List<Filter>

            try {
                filters = filterService.getFiltersFromRequest(call.request)
            } catch (e: UnknownFilterException) {
                call.respond(HttpStatusCode.BadRequest, e.message.orEmpty())
                return@get
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.BadRequest, e.message.orEmpty())
                return@get
            }

            val filteredCities = partnerService.getFilteredCities(filters)

            call.respond(filteredCities)
        }
    }
}