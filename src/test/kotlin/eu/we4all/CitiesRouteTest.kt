package eu.we4all

import eu.we4all.map.data.City
import eu.we4all.map.logic.PartnerService
import eu.we4all.map.plugins.configureHTTP
import eu.we4all.map.plugins.configureRouting
import eu.we4all.map.plugins.installContentNegotiation
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

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
                map["foo"] = City("Foo", 1.0f, 2.0f, 3)
                map["bar"] = City("Bar", 4.0f, 5.0f, 6)
            }

            override fun getAllCities(): Map<String, City> {
                return map
            }
        }

        withTestApplication(partnerServiceMock) {
            client.get("/cities").apply {
                assertEquals(HttpStatusCode.OK, status)
                assertEquals("""{"foo":{"fullName":"Foo","lat":1.0,"long":2.0,"partners":3},"bar":{"fullName":"Bar","lat":4.0,"long":5.0,"partners":6}}""", bodyAsText())
            }
        }
    }
}