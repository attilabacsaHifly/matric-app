package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class Country(@SerializedName("en") val en: String, @SerializedName("hu") val hu: String)
