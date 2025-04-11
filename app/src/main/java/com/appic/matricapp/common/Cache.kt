package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

interface Cache {
    fun cacheInfo(info: Info)
    fun cacheVehicleInfo(vehicleInfo: VehicleInfo)
    fun getInfo(): Info?
    fun getVehicleInfo(): VehicleInfo?

    /**
     * Add a single country vignette to the selection.
     */
    fun addVignetteToSelected(vignette: Vignette)

    /**
     * Add one or more yearly county vignette(s) to the selection.
     */
    fun addVignettesToSelected(vignettes: List<Vignette>)
    fun removeVignetteFromSelected(vignette: Vignette)
}
