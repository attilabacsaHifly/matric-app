package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class HighwayOrder(
    @SerializedName("category") val category: String,
    @SerializedName("cost") val cost: Double,
    @SerializedName("type") val type: String
)
