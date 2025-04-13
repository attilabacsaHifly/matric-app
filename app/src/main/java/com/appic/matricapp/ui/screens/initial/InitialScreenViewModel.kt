package com.appic.matricapp.ui.screens.initial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appic.matricapp.R
import com.appic.matricapp.common.DataCache
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
    private val dataCache: DataCache
) : ViewModel() {

    private val screenStateFlow = MutableStateFlow<InitialScreenState>(InitialScreenState.Created)
    val screenState = screenStateFlow.asStateFlow()

    fun onCreated() {
        viewModelScope.launch(ioDispatcher) {
            screenStateFlow.emit(InitialScreenState.Loading)

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

    fun onNameVignettePairSelected(nameVignettePair: Pair<String, Vignette>) {
        (screenStateFlow.value as? InitialScreenState.Loaded)?.let {
            val updatedValue = it.copy(selectedNameVignettePair = nameVignettePair)
            screenStateFlow.tryEmit(updatedValue)
        }
    }

    fun onConfirmPurchase() {
        (screenStateFlow.value as? InitialScreenState.Loaded)?.selectedNameVignettePair?.let {
            dataCache.addNameVignettePairToSelected(it)
        }
    }

    fun clearSelection() {
        val loadedState = screenStateFlow.value as? InitialScreenState.Loaded

        loadedState?.selectedNameVignettePair?.let {
            dataCache.removeNameVignettePairFromSelected(it)
        }
        loadedState?.selectedNameVignettePair = null
    }

    private suspend fun onScreenLoaded(info: Info?, vehicleInfo: VehicleInfo?) {
        if (info == null || vehicleInfo == null) {
            screenStateFlow.emit(InitialScreenState.Error)
        } else {
            val filteredInfo = info.copy(vignettes = info.vignettes.filter { vignette ->
                vignette.vignetteTypes.all { vignetteType ->
                    vignetteType == VignetteType.DAY ||
                            vignetteType == VignetteType.WEEK ||
                            vignetteType == VignetteType.MONTH ||
                            vignetteType == VignetteType.YEAR
                }
            })

            val displayedNameVignettePairs = filteredInfo.vignettes.map {
                Pair(it.vignetteTypes.first().toStringResource(), it)
            }
            screenStateFlow.emit(
                InitialScreenState.Loaded(displayedNameVignettePairs, vehicleInfo, null)
            )

            dataCache.cacheInfo(info)
            dataCache.cacheVehicleInfo(vehicleInfo)
        }
    }

    private fun VignetteType.toStringResource(): Int {
        return when (this) {
            VignetteType.DAY -> R.string.vignette_type_day
            VignetteType.WEEK -> R.string.vignette_type_week
            VignetteType.MONTH -> R.string.vignette_type_month
            else -> R.string.vignette_type_year
        }
    }
}
