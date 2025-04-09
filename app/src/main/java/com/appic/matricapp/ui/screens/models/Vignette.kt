package com.appic.matricapp.ui.screens.models

import com.appic.matricapp.network.models.VignetteCategory
import com.appic.matricapp.network.models.VignetteType

data class Vignette(
    val category: VignetteCategory,
    val types: List<VignetteType>,
    val cost: Double,
    val trxFee: Double
)
