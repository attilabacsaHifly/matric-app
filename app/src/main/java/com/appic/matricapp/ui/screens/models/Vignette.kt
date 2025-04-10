package com.appic.matricapp.ui.screens.models

import com.appic.matricapp.network.models.Category
import com.appic.matricapp.network.models.VignetteCategory
import com.appic.matricapp.network.models.VignetteType
import kotlinx.serialization.Serializable

@Serializable
data class Vignette(
    val category: Category,
    val vignetteCategory: VignetteCategory,
    val vignetteTypes: List<VignetteType>,
    val cost: Double,
    val trxFee: Double
)
