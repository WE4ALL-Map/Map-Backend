package eu.we4all.map.routes


import eu.we4all.map.data.serializers.SerializableUUID
import eu.we4all.map.logic.PartnerService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.pipeline.*

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
        fun PipelineContext<Unit, ApplicationCall>.getCityId(): SerializableUUID? =
            try {
                call.parameters["city_id"]?.let { SerializableUUID.fromString(it) }
            } catch (e: IllegalArgumentException) {
                null
            }

        get {
            val cityId = getCityId()

            if (cityId == null) {
                call.respond(HttpStatusCode.BadRequest, "Missing city id in query parameters")
                return@get
            }

            val city = partnerService.getCity(cityId)

            if (city == null) {
                call.respond(HttpStatusCode.NotFound, "Couldn't find a city with the ID $cityId.")
                return@get
            }

            call.respond(city)
        }
    }
}
