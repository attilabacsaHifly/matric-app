package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class GetVehicleResponse(
    @SerializedName("country") val country: Country,
    @SerializedName("internationalRegistrationCode") val internationalRegistrationCode: String,
    @SerializedName("name") val name: String,
    @SerializedName("plate") val plate: String,
    @SerializedName("statusCode") val statusCode: String,
    @SerializedName("type") val type: String,
    @SerializedName("vignetteType") val vignetteType: String
)
