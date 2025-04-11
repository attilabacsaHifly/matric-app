package com.appic.matricapp.ui.screens.initial

import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo

sealed interface InitialScreenState {
    data object Created : InitialScreenState
    data object Loading : InitialScreenState
    data class Loaded(val info: Info, val vehicleInfo: VehicleInfo) : InitialScreenState
    data object Error : InitialScreenState
}
