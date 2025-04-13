package com.appic.matricapp.ui.screens.countyvignettes

import com.appic.matricapp.ui.screens.models.Vignette

data class CountyVignettesScreenState(
    val nameNameVignettePairs: List<Pair<String, Vignette>>,
    val amountToPay: Double,
    val isConfirmPurchaseEnabled: Boolean,
    val selectedNameVignettePairs: List<Pair<String, Vignette>>
)
