package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.lifecycle.ViewModel
import com.appic.matricapp.common.DataCache
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class PurchaseConfirmationScreenViewModel @Inject constructor(
    dataCache: DataCache
) : ViewModel() {

    private val screenStateFlow = MutableStateFlow<PurchaseConfirmationScreenState>(
        PurchaseConfirmationScreenState.Created(
            vehiclePlate = dataCache.getVehicleInfo()?.vehiclePlate ?: "",
            displayedNameNameVignettePairs = dataCache.getSelectedNameVignettePairs()
        )
    )
    val screenState = screenStateFlow.asStateFlow()
}
