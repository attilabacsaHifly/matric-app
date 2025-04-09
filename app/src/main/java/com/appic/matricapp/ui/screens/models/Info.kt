package com.appic.matricapp.ui.screens.models

import com.appic.matricapp.network.models.GetInfoResponse
import com.appic.matricapp.network.models.VignetteCategory

data class Info(val vignettes: List<Vignette>, val counties: List<County>) {

    companion object {
        fun map(response: GetInfoResponse?): Info? {
            return if (response != null) {
                Info(
                    vignettes = response.payload.highwayVignettes.map { highwayVignette ->
                        val vignetteCategory = response.payload.vehicleCategories
                            .firstOrNull { vehicleCategory ->
                                vehicleCategory.category == highwayVignette.vehicleCategory
                            }?.vignetteCategory ?: VignetteCategory.D1

                        Vignette(
                            category = vignetteCategory,
                            types = highwayVignette.vignetteType,
                            cost = highwayVignette.cost,
                            trxFee = highwayVignette.trxFee
                        )
                    },
                    counties = response.payload.counties.map {
                        County(it.name)
                    }
                )
            } else {
                null
            }
        }
    }
}
