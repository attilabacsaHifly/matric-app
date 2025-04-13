package com.appic.matricapp.interactor

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

interface HighwayVignetteInteractor {
    suspend fun getInfo(): Info?
    suspend fun getVehicleInfo(): VehicleInfo?
    suspend fun orderVignettes(vignettes: List<Vignette>): Boolean
}
