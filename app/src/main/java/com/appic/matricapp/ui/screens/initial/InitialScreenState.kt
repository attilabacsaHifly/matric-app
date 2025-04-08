package com.appic.matricapp.ui.screens.initial

sealed interface InitialScreenState {
    data object Created : InitialScreenState
    data object Loading : InitialScreenState
    data class Loaded(val vehicleOwnerName: String, val vehiclePlate: String) : InitialScreenState
    data object Error : InitialScreenState
}
