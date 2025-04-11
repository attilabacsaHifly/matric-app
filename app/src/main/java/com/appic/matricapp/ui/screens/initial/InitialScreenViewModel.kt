package com.appic.matricapp.ui.screens.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appic.matricapp.common.Cache
import com.appic.matricapp.injection.IODispatcher
import com.appic.matricapp.interactor.HighwayVignetteInteractor
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class InitialScreenViewModel @Inject constructor(
    @IODispatcher private val ioDispatcher: CoroutineDispatcher,
    private val interactor: HighwayVignetteInteractor,
    private val cache: Cache
) : ViewModel() {

    private val initialScreenStateFlow =
        MutableStateFlow<InitialScreenState>(InitialScreenState.Created)
    val initialScreenState = initialScreenStateFlow.asStateFlow()

    fun loadScreen() {
        viewModelScope.launch(ioDispatcher) {
            initialScreenStateFlow.emit(InitialScreenState.Loading)

            val infoDeferred = async {
                runCatching {
                    interactor.getInfo()
                }.getOrNull()
            }
            val vehicleInfoDeferred = async {
                runCatching {
                    interactor.getVehicleInfo()
                }.getOrNull()
            }

            onScreenLoaded(infoDeferred.await(), vehicleInfoDeferred.await())
        }
    }

    fun onConfirmPurchase(vignette: Vignette) {
        cache.addVignetteToSelected(vignette)
    }

    private suspend fun onScreenLoaded(info: Info?, vehicleInfo: VehicleInfo?) {
        if (info == null || vehicleInfo == null) {
            initialScreenStateFlow.emit(InitialScreenState.Error)
        } else {
            val filteredInfo = info.copy(vignettes = info.vignettes.filter { vignette ->
                vignette.vignetteTypes.all { vignetteType ->
                    vignetteType == VignetteType.DAY ||
                            vignetteType == VignetteType.WEEK ||
                            vignetteType == VignetteType.MONTH
                }
            })

            initialScreenStateFlow.emit(InitialScreenState.Loaded(filteredInfo, vehicleInfo))

            cache.cacheInfo(info)
            cache.cacheVehicleInfo(vehicleInfo)
        }
    }
}
