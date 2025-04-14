package com.appic.matricapp.interactor

import android.util.Log
import com.appic.matricapp.network.api.HighwayVignetteAPI
import com.appic.matricapp.network.models.HighwayOrder
import com.appic.matricapp.network.models.OrderVignettesRequest
import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

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

    override suspend fun orderVignettes(vignettes: List<Vignette>): Boolean {
        val orders = vignettes.map {
            HighwayOrder(
                category = it.category.name,
                cost = (it.cost + it.trxFee).toFloat(),
                type = it.vignetteTypes.first().name
            )
        }
        val response = api.orderVignettes(OrderVignettesRequest(orders))

        if (!response.isSuccessful) {
            Log.e(null, response.message())
        }

        return response.isSuccessful && response.body()?.statusCode == STATUS_CODE_SUCCESS
    }

    companion object {
        private const val STATUS_CODE_SUCCESS = "OK"
    }
}

