package com.appic.matricapp.ui.screens.initial

import androidx.lifecycle.ViewModel
import com.appic.matricapp.interactor.HighwayVignetteInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class InitialScreenViewModel @Inject constructor(
    private val interactor: HighwayVignetteInteractor
) : ViewModel() {

    private val initialScreenStateFlow =
        MutableStateFlow<InitialScreenState>(InitialScreenState.Created)
    val initialScreenState = initialScreenStateFlow.asStateFlow()
}
