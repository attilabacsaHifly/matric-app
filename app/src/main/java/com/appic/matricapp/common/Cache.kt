package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo

interface Cache {
    fun cacheInfo(info: Info)
    fun cacheVehicleInfo(vehicleInfo: VehicleInfo)
    fun getInfo(): Info?
    fun getVehicleInfo(vehicleInfo: VehicleInfo): VehicleInfo?
}
