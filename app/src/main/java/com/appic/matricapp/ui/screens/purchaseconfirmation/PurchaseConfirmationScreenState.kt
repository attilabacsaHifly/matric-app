package com.appic.matricapp.ui.screens.purchaseconfirmation

import com.appic.matricapp.ui.screens.models.Vignette

sealed interface PurchaseConfirmationScreenState {
    data class Created(
        val vehiclePlate: String,
        val nameVignettePairs: List<Pair<String, Vignette>>
    ) : PurchaseConfirmationScreenState

    data object Loading : PurchaseConfirmationScreenState
    data object Success : PurchaseConfirmationScreenState
    data object Error : PurchaseConfirmationScreenState
}
