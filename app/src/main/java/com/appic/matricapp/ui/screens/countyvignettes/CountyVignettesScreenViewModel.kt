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

    private val displayedNameNameVignettePairsFlow =
        MutableStateFlow(initDisplayedNameNameVignettePairs())
    val displayedNameVignettePairs = displayedNameNameVignettePairsFlow.asStateFlow()

    private val amountToPayFlow = MutableStateFlow(0.0)
    val amountToPay = amountToPayFlow.asStateFlow()

    private val isConfirmPurchaseEnabledFlow = MutableStateFlow(false)
    val isConfirmPurchaseEnabled = isConfirmPurchaseEnabledFlow.asStateFlow()

    private val selectedNameVignettePairs = mutableListOf<Pair<String, Vignette>>()

    fun onVignetteSelected(nameVignettePair: Pair<String, Vignette>) {
        selectedNameVignettePairs.add(nameVignettePair)

        amountToPayFlow.tryEmit(selectedNameVignettePairs.sumOf { it.second.cost })
        isConfirmPurchaseEnabledFlow.tryEmit(selectedNameVignettePairs.any())
    }

    fun onVignetteDeselected(nameVignettePair: Pair<String, Vignette>) {
        selectedNameVignettePairs.remove(nameVignettePair)

        amountToPayFlow.tryEmit(selectedNameVignettePairs.sumOf { it.second.cost })
        isConfirmPurchaseEnabledFlow.tryEmit(selectedNameVignettePairs.any())
    }

    fun onConfirmPurchase() {
        dataCache.addNameVignettePairsToSelected(selectedNameVignettePairs)
    }

    private fun initDisplayedNameNameVignettePairs(): List<Pair<String, Vignette>> {
        val cachedInfo = dataCache.getInfo() ?: return emptyList()

        val yearlyVignettes = cachedInfo.vignettes.filter { vignette ->
            vignette.vignetteTypes.none { vignetteType ->
                vignetteType == VignetteType.DAY ||
                        vignetteType == VignetteType.WEEK ||
                        vignetteType == VignetteType.MONTH ||
                        vignetteType == VignetteType.YEAR
            }
        }

        return cachedInfo.counties.mapNotNull { county ->
            val matchingVignette = yearlyVignettes.firstOrNull { vignette ->
                vignette.vignetteTypes.any { vignetteType ->
                    vignetteType.name == county.id
                }
            }

            Pair(county.name, matchingVignette ?: return@mapNotNull null)
        }.sortedBy { pair -> pair.first }
    }
}
