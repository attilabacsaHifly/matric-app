package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class GetInfoResponse(
    @SerializedName("payload") val payload: GetInfoResponsePayload,
    @SerializedName("requestId") val requestId: String,
    @SerializedName("statusCode") val statusCode: String
)
