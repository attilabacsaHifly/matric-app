package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class VehicleCategory(
    @SerializedName("category") val category: Category,
    @SerializedName("name") val name: VehicleName,
    @SerializedName("vignetteCategory") val vignetteCategory: VignetteCategory
)
