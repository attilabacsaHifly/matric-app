package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appic.matricapp.common.DataCache
import com.appic.matricapp.injection.IODispatcher
import com.appic.matricapp.interactor.HighwayVignetteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class PurchaseConfirmationScreenViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val dataCache: DataCache,
    private val interactor: HighwayVignetteInteractor
) : ViewModel() {

    private val screenStateFlow = MutableStateFlow<PurchaseConfirmationScreenState>(
        PurchaseConfirmationScreenState.Created(
            vehiclePlate = dataCache.getVehicleInfo()?.vehiclePlate ?: "",
            nameVignettePairs = dataCache.getSelectedNameVignettePairs()
        )
    )
    val screenState = screenStateFlow.asStateFlow()

    fun confirmPurchase() {
        viewModelScope.launch(ioDispatcher) {
            screenStateFlow.emit(PurchaseConfirmationScreenState.Loading)

            val selectedVignettePairs = dataCache.getSelectedNameVignettePairs()
            val successfulOrder = runCatching {
                interactor.orderVignettes(selectedVignettePairs.map { it.second })
            }.getOrElse { false }

            if (successfulOrder) {
                screenStateFlow.emit(PurchaseConfirmationScreenState.Success)
            } else {
                screenStateFlow.emit(PurchaseConfirmationScreenState.Error)
            }

        }
    }
}
