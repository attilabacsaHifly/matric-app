package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class OrderVignettesRequest(
    @SerializedName("highwayOrders") val highwayOrders: List<HighwayOrder>
)
