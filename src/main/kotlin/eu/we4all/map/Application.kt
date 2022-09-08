package eu.we4all.map

import eu.we4all.map.logic.impl.PartnerServiceImpl
import eu.we4all.map.plugins.configureHTTP
import eu.we4all.map.plugins.configureRouting
import eu.we4all.map.plugins.installContentNegotiation
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureHTTP()
        installContentNegotiation()
        configureRouting(PartnerServiceImpl("data.json"))
    }.start(wait = true)
}
