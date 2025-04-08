package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class VehicleName(@SerializedName("en") val en: String, @SerializedName("hu") val hu: String)
