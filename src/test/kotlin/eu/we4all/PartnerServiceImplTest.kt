package eu.we4all

import eu.we4all.map.logic.impl.PartnerServiceImpl
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class PartnerServiceImplTest {
    @Test
    fun test_PartnerServiceImpl_loadsJsonCorrectly() {
        val partnerService = PartnerServiceImpl("test_data.json")
        val loadedCities = partnerService.getAllCities()

        assertContains(loadedCities, "foo")
        val fooCity = loadedCities["foo"]!!
        assertEquals(1.0f, fooCity.latitude)
        assertEquals(2.0f, fooCity.longitude)
        assertEquals(3, fooCity.partners)

        assertContains(loadedCities, "bar")
        val barCity = loadedCities["bar"]!!
        assertEquals(4.0f, barCity.latitude)
        assertEquals(5.0f, barCity.longitude)
        assertEquals(6, barCity.partners)
    }
}