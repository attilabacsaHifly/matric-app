package com.appic.matricapp.interactor

import android.util.Log
import com.appic.matricapp.network.api.HighwayVignetteAPI
import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo

class HighwayVignetteInteractorImpl(
    private val api: HighwayVignetteAPI
) : HighwayVignetteInteractor {

    override suspend fun getInfo(): Info? {
        val response = api.getInfo()

        return if (response.isSuccessful) {
            Info.map(response.body())
        } else {
            Log.e(null, response.message())
            null
        }
    }

    override suspend fun getVehicleInfo(): VehicleInfo? {
        val response = api.getVehicle()

        return if (response.isSuccessful) {
            VehicleInfo.map(response.body())
        } else {
            Log.e(null, response.message())
            null
        }
    }

    override suspend fun orderVignettes() {
        TODO("Not yet implemented")
    }
}

