package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

class CacheImpl : Cache {

    private var info: Info? = null
    private var vehicleInfo: VehicleInfo? = null

    private val selectedVignettes = mutableListOf<Vignette>()

    override fun cacheInfo(info: Info) {
        this.info = info
    }

    override fun cacheVehicleInfo(vehicleInfo: VehicleInfo) {
        this.vehicleInfo = vehicleInfo
    }

    override fun getInfo(): Info? {
        return info
    }

    override fun getVehicleInfo(): VehicleInfo? {
        return vehicleInfo
    }

    override fun addVignetteToSelected(vignette: Vignette) {
        // Remove any yearly county vignettes to avoid possible conflict between selections
        if (selectedVignettes.any()) {
            selectedVignettes.clear()
        }

        selectedVignettes.add(vignette)
    }

    override fun addVignettesToSelected(vignettes: List<Vignette>) {
        // Remove selected country vignette to avoid possible conflict between selections
        if (selectedVignettes.any()) {
            selectedVignettes.clear()
        }

        selectedVignettes.addAll(vignettes)
    }

    override fun removeVignetteFromSelected(vignette: Vignette) {
        selectedVignettes.remove(vignette)
    }
}
