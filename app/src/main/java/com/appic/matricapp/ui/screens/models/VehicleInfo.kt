package com.appic.matricapp.ui.screens.models

import com.appic.matricapp.network.models.GetVehicleResponse

data class VehicleInfo(val vehicleOwnerName: String, val vehiclePlate: String) {

    companion object {
        fun map(response: GetVehicleResponse?): VehicleInfo? {
            return if (response != null) {
                VehicleInfo(response.name, response.plate)
            } else {
                null
            }
        }
    }
}
