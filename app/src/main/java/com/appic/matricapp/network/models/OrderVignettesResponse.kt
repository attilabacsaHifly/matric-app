package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class OrderVignettesResponse(
    @SerializedName("receivedOrders") val receivedOrders: List<ReceivedOrder>,
    @SerializedName("statusCode") val statusCode: String
)
