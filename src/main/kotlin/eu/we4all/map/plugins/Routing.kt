package eu.we4all.map.plugins

import eu.we4all.map.logic.impl.PartnerServiceImpl
import eu.we4all.map.routes.cities
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureRouting() {
    val partnerService = PartnerServiceImpl("data.json")

    routing {
        route("/cities") {
            cities(partnerService)
        }
    }
}
