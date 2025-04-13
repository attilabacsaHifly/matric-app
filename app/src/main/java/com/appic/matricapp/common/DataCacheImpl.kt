package com.appic.matricapp.common

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

class DataCacheImpl : DataCache {

    private var info: Info? = null
    private var vehicleInfo: VehicleInfo? = null

    private val selectedNameVignettePairs = mutableListOf<Pair<String, Vignette>>()

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

    override fun addNameVignettePairToSelected(pair: Pair<String, Vignette>) {
        // Clear selection to avoid possible conflict between previous selections
        if (selectedNameVignettePairs.any()) {
            selectedNameVignettePairs.clear()
        }

        selectedNameVignettePairs.add(pair)
    }

    override fun addNameVignettePairsToSelected(pairs: List<Pair<String, Vignette>>) {
        // Clear selection to avoid possible conflict between previous selections
        if (selectedNameVignettePairs.any()) {
            selectedNameVignettePairs.clear()
        }

        selectedNameVignettePairs.addAll(pairs)
    }

    override fun getSelectedNameVignettePairs(): List<Pair<String, Vignette>> {
        return selectedNameVignettePairs
    }

    override fun removeNameVignettePairFromSelected(pair: Pair<String, Vignette>) {
        selectedNameVignettePairs.remove(pair)
    }
}
