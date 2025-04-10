package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class ReceivedOrder(
    @SerializedName("category") val category: Category,
    @SerializedName("cost") val cost: Double,
    @SerializedName("type") val type: VignetteType
)
