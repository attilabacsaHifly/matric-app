package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class GetInfoResponsePayload(
    @SerializedName("counties") val counties: List<County>,
    @SerializedName("highwayVignettes") val highwayVignettes: List<HighwayVignette>,
    @SerializedName("vehicleCategories") val vehicleCategories: List<VehicleCategory>
)
