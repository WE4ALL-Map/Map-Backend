package eu.we4all.map.plugins

import eu.we4all.map.logic.FilterService
import eu.we4all.map.logic.PartnerService
import eu.we4all.map.routes.cities
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting(partnerService: PartnerService, filterService: FilterService) {
    routing {
        route("/cities") {
            cities(partnerService, filterService)
        }
    }
}
