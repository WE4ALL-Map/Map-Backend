package eu.we4all.map.routes

import eu.we4all.map.logic.PartnerService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.cities(partnerService: PartnerService) {
    get {
        val cities = partnerService.getAllCities()
        call.respond(cities)
    }
}