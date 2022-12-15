package eu.we4all.map.routes


import eu.we4all.map.data.City
import eu.we4all.map.data.DetailedBorough
import eu.we4all.map.data.serializers.SerializableUUID
import eu.we4all.map.logic.PartnerService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

fun PipelineContext<Unit, ApplicationCall>.getCityId(): SerializableUUID? =
    try {
        call.parameters["city_id"]?.let { SerializableUUID.fromString(it) }
    } catch (e: IllegalArgumentException) {
        null
    }

suspend fun PipelineContext<Unit, ApplicationCall>.getCityOrError(partnerService: PartnerService): City? {
    val cityId = getCityId()

    if (cityId == null) {
        call.respond(HttpStatusCode.BadRequest, "Missing city id in query parameters")
        return null
    }

    val city = partnerService.getCity(cityId)

    if (city == null) {
        call.respond(HttpStatusCode.NotFound, "Couldn't find a city with the ID $cityId.")
        return null
    }

    return city
}

fun Route.cities(partnerService: PartnerService) {
    get {
        val cities = partnerService.getCities()
        call.respond(cities)
    }

    route("/names") {
        get {
            val cityNames = partnerService.getCityNames()
            call.respond(cityNames)
        }
    }

    route("/{city_id}") {
        get {
            val city = getCityOrError(partnerService) ?: return@get

            call.respond(city)
        }

        route("/boroughs") {
            boroughs(partnerService)
        }

        // /api/cities/2334234/boroughs/2342345
    }
}

fun PipelineContext<Unit, ApplicationCall>.getBoroughId(): SerializableUUID? =
    try {
        call.parameters["borough_id"]?.let { SerializableUUID.fromString(it) }
    } catch (e: IllegalArgumentException) {
        null
    }

fun Route.boroughs(partnerService: PartnerService) {
    get {
        val city = getCityOrError(partnerService) ?: return@get

        call.respond(city.boroughs)
    }

    route("/{borough_id}") {
        get {
            val city = getCityOrError(partnerService) ?: return@get

            val boroughId = getBoroughId()

            if (boroughId == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing borough id in query parameters")
                return@get
            }

            val borough = city.boroughs.find { it.id == boroughId }

            if (borough == null) {
                call.respond(HttpStatusCode.NotFound, "Couldn't find a borough with the ID $boroughId.")
                return@get
            }

            val detailedBorough = DetailedBorough(
                borough.id,
                borough.displayName,
                borough.center,
                city.partners.filter { it.borough == boroughId },
            )

            call.respond(detailedBorough)
        }
    }
}
