package com.appic.matricapp.network.models

import com.google.gson.annotations.SerializedName

data class County(@SerializedName("id") val id: String, @SerializedName("name") val name: String)
