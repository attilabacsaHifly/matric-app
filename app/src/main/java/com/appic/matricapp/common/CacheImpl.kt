package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo

class CacheImpl : Cache {

    private var info: Info? = null
    private var vehicleInfo: VehicleInfo? = null

    override fun cacheInfo(info: Info) {
        this.info = info
    }

    override fun cacheVehicleInfo(vehicleInfo: VehicleInfo) {
        this.vehicleInfo = vehicleInfo
    }

    override fun getInfo(): Info? {
        return info
    }

    override fun getVehicleInfo(vehicleInfo: VehicleInfo): VehicleInfo? {
        return vehicleInfo
    }

}
