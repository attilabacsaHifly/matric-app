package com.appic.matricapp.navigation

import com.appic.matricapp.ui.screens.models.Vignette
import kotlinx.serialization.Serializable

sealed interface NavigationDestination {
    @Serializable
    data object Initial : NavigationDestination

    @Serializable
    data object CountyVignettes : NavigationDestination

    @Serializable
    data class PurchaseConfirmation(
        val vehiclePlate: String,
        val vignettes: List<Vignette>
    ) : NavigationDestination

    @Serializable
    data object PurchaseSuccess : NavigationDestination
}
