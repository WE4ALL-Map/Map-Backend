package eu.we4all

import eu.we4all.map.data.City
import eu.we4all.map.logic.PartnerService
import eu.we4all.map.plugins.configureHTTP
import eu.we4all.map.plugins.configureRouting
import eu.we4all.map.plugins.installContentNegotiation
import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import kotlin.reflect.jvm.internal.impl.util.ArrayMap

class CitiesRouteTest {
    private fun withTestApplication(testPartnerService: PartnerService, builder: suspend ApplicationTestBuilder.() -> Unit) = testApplication {
        application {
            configureHTTP()
            configureRouting(testPartnerService)
            installContentNegotiation()
        }

        builder.invoke(this)
    }

    @Test
    fun `GET cities`() {
        val partnerServiceMock = object : PartnerServiceMockBase() {
            val map = LinkedHashMap<String, City>()

            init {
                map["foo"] = City(1.0f, 2.0f, 3)
                map["bar"] = City(4.0f, 5.0f, 6)
            }

            override fun getAllCities(): Map<String, City> {
                return map
            }
        }

        withTestApplication(partnerServiceMock) {
            client.get("/cities").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("""{"foo":{"lat":1.0,"long":2.0,"partners":3},"bar":{"lat":4.0,"long":5.0,"partners":6}}""", bodyAsText())
            }
        }
    }
}