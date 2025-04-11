package com.appic.matricapp.ui.screens.countyvignettes

import androidx.lifecycle.ViewModel
import com.appic.matricapp.common.Cache
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.screens.models.Vignette
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class CountyVignettesScreenViewModel @Inject constructor(private val cache: Cache) : ViewModel() {

    private val countyNameVignettePairsFlow = MutableStateFlow(initCountyNameVignettePairs())
    val countyNameVignettePairs = countyNameVignettePairsFlow.asStateFlow()

    private val amountToPayFlow = MutableStateFlow(0.0)
    val amountToPay = amountToPayFlow.asStateFlow()

    private val isContinueEnabledFlow = MutableStateFlow(false)
    val isContinueEnabled = isContinueEnabledFlow.asStateFlow()

    private val selectedVignettes = mutableListOf<Vignette>()

    fun onVignetteSelected(vignette: Vignette) {
        selectedVignettes.add(vignette)

        amountToPayFlow.tryEmit(selectedVignettes.sumOf { it.cost })
        isContinueEnabledFlow.tryEmit(selectedVignettes.any())
    }

    fun onVignetteDeselected(vignette: Vignette) {
        selectedVignettes.remove(vignette)

        amountToPayFlow.tryEmit(selectedVignettes.sumOf { it.cost })
        isContinueEnabledFlow.tryEmit(selectedVignettes.any())
    }

    fun onConfirmPurchase() {
        cache.addVignettesToSelected(selectedVignettes)
    }

    private fun initCountyNameVignettePairs(): List<Pair<String, Vignette>> {
        val cachedInfo = cache.getInfo() ?: return emptyList()

        val yearlyVignettes = cachedInfo.vignettes.filter { vignette ->
            vignette.vignetteTypes.none { vignetteType ->
                vignetteType == VignetteType.DAY ||
                        vignetteType == VignetteType.WEEK ||
                        vignetteType == VignetteType.MONTH
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
