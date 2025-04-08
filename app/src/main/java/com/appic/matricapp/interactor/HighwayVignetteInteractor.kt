package com.appic.matricapp.interactor

import com.appic.matricapp.ui.screens.initial.VehicleInfo

interface HighwayVignetteInteractor {
    suspend fun getInfo()
    suspend fun getVehicleInfo(): VehicleInfo?
    suspend fun orderVignettes()
}
