package com.appic.matricapp.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationDestination {
    @Serializable
    data object Initial : NavigationDestination

    @Serializable
    data object CountyVignettes : NavigationDestination

    @Serializable
    data object PurchaseConfirmation : NavigationDestination

    @Serializable
    data object PurchaseSuccess : NavigationDestination
}
