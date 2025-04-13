package com.appic.matricapp.ui.screens.initial

import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

sealed interface InitialScreenState {
    data object Created : InitialScreenState
    data object Loading : InitialScreenState
    data class Loaded(
        val displayedNameVignettePairs: List<Pair<Int, Vignette>>,
        val vehicleInfo: VehicleInfo
    ) : InitialScreenState

    data object Error : InitialScreenState
}
