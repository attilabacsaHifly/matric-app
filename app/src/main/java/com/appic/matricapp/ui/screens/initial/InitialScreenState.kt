package com.appic.matricapp.ui.screens.initial

sealed interface InitialScreenState {
    data object Created : InitialScreenState
    data object Loading : InitialScreenState
    data class Loaded(val vehicleInfo: VehicleInfo) : InitialScreenState
    data object Error : InitialScreenState
}
