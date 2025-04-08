package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class VehicleCategory(
    @SerializedName("category") val category: String,
    @SerializedName("name") val name: VehicleName,
    @SerializedName("vignetteCategory") val vignetteCategory: String
)
