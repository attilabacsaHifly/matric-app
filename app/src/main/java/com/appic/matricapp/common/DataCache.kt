package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

interface DataCache {
    fun cacheInfo(info: Info)
    fun cacheVehicleInfo(vehicleInfo: VehicleInfo)
    fun getInfo(): Info?
    fun getVehicleInfo(): VehicleInfo?

    /**
     * Add a single country vignette to the selection.
     */
    fun addNameVignettePairToSelected(pair: Pair<String, Vignette>)

    /**
     * Add one or more yearly county vignette(s) to the selection.
     */
    fun addNameVignettePairsToSelected(pairs: List<Pair<String, Vignette>>)
    fun getSelectedNameVignettePairs(): List<Pair<String, Vignette>>
    fun removeNameVignettePairFromSelected(pair: Pair<String, Vignette>)
}
