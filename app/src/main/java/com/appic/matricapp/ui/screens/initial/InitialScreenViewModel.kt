package com.appic.matricapp.ui.screens.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appic.matricapp.interactor.HighwayVignetteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class InitialScreenViewModel @Inject constructor(
    private val interactor: HighwayVignetteInteractor
) : ViewModel() {

    private val initialScreenStateFlow =
        MutableStateFlow<InitialScreenState>(InitialScreenState.Created)
    val initialScreenState = initialScreenStateFlow.asStateFlow()

    fun loadScreen() {
        viewModelScope.launch(Dispatchers.IO) {
            initialScreenStateFlow.emit(InitialScreenState.Loading)

            val vehicleInfo = interactor.getVehicleInfo()
            if (vehicleInfo != null) {
                initialScreenStateFlow.emit(InitialScreenState.Loaded(vehicleInfo))
            } else {
                initialScreenStateFlow.emit(InitialScreenState.Error)
            }
        }
    }
}
