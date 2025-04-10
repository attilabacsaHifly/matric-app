package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class HighwayVignette(
    @SerializedName("cost") val cost: Double,
    @SerializedName("sum") val sum: Double,
    @SerializedName("trxFee") val trxFee: Double,
    @SerializedName("vehicleCategory") val vehicleCategory: Category,
    @SerializedName("vignetteType") val vignetteTypes: List<VignetteType>
)
