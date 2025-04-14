package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import junit.framework.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

// Unfortunately due to lack of time the test coverage is not complete.
class DataCacheTest {

    private lateinit var dataCache: DataCache

    @Before
    fun setup() {
        dataCache = DataCacheImpl()
    }

    @Test
    fun `when cacheInfo is called then Info is cached`() {
        val info = Info(listOf(), listOf())

        dataCache.cacheInfo(info)

        assertEquals(info, dataCache.getInfo())
    }

    @Test
    fun `when cacheInfo is not called then Info is null`() {
        assertNull(dataCache.getInfo())
    }

    @Test
    fun `when cacheVehicleInfo is called then VehicleInfo is cached`() {
        val vehicleInfo = VehicleInfo("Raphael Foley", "ABC - 123")

        dataCache.cacheVehicleInfo(vehicleInfo)

        assertEquals(vehicleInfo, dataCache.getVehicleInfo())
    }

    @Test
    fun `when cacheVehicleInfo is not called then VehicleInfo is null`() {
        assertNull(dataCache.getVehicleInfo())
    }
}
