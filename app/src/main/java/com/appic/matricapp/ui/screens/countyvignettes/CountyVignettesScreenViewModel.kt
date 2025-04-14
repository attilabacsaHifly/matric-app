package com.appic.matricapp.ui.screens.countyvignettes

import androidx.lifecycle.ViewModel
import com.appic.matricapp.common.DataCache
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.screens.models.Vignette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CountyVignettesScreenViewModel @Inject constructor(
    private val dataCache: DataCache
) : ViewModel() {

    private val screenStateFlow = MutableStateFlow(initScreenState())
    val screenState = screenStateFlow.asStateFlow()

    fun onVignetteSelected(nameVignettePair: Pair<String, Vignette>) {
        screenStateFlow.value?.let { screenState ->
            val updatedSelectedNameVignettePairs =
                screenState.selectedNameVignettePairs.plus(nameVignettePair)
            val updatedScreenState = screenState.copy(
                selectedNameVignettePairs = updatedSelectedNameVignettePairs,
                amountToPay = updatedSelectedNameVignettePairs.sumOf { it.second.cost },
                isConfirmPurchaseEnabled = true
            )

            screenStateFlow.tryEmit(updatedScreenState)
        }
    }

    fun onVignetteDeselected(nameVignettePair: Pair<String, Vignette>) {
        screenStateFlow.value?.let { screenState ->
            val updatedSelectedNameVignettePairs =
                screenState.selectedNameVignettePairs.minus(nameVignettePair)
            val updatedScreenState = screenState.copy(
                selectedNameVignettePairs = updatedSelectedNameVignettePairs,
                amountToPay = updatedSelectedNameVignettePairs.sumOf { it.second.cost },
                isConfirmPurchaseEnabled = updatedSelectedNameVignettePairs.any()
            )

            screenStateFlow.tryEmit(updatedScreenState)
        }
    }

    fun onConfirmPurchase() {
        screenStateFlow.value?.let {
            dataCache.addNameVignettePairsToSelected(it.selectedNameVignettePairs)
        }
    }

    private fun initScreenState(): CountyVignettesScreenState? {
        val cachedInfo = dataCache.getInfo() ?: return null

        val yearlyVignettes = cachedInfo.vignettes.filter { vignette ->
            vignette.vignetteTypes.none { vignetteType ->
                vignetteType == VignetteType.DAY ||
                        vignetteType == VignetteType.WEEK ||
                        vignetteType == VignetteType.MONTH ||
                        vignetteType == VignetteType.YEAR
            }
        }

        val nameNameVignettePairs = cachedInfo.counties.mapNotNull { county ->
            val matchingVignette = yearlyVignettes.firstOrNull { vignette ->
                vignette.vignetteTypes.any { vignetteType ->
                    vignetteType.name == county.id
                }
            }

            Pair(county.name, matchingVignette ?: return@mapNotNull null)
        }.sortedBy { pair -> pair.first }

        return CountyVignettesScreenState(
            nameNameVignettePairs = nameNameVignettePairs,
            amountToPay = 0.0,
            isConfirmPurchaseEnabled = false,
            selectedNameVignettePairs = mutableListOf()
        )
    }
}
