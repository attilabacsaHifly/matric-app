package com.appic.matricapp.ui.screens.initial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R
import com.appic.matricapp.network.models.Category
import com.appic.matricapp.network.models.VignetteCategory
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.screens.initial.components.InitialScreenCountryVignettesCard
import com.appic.matricapp.ui.screens.initial.components.InitialScreenVehicleInfoCard
import com.appic.matricapp.ui.screens.initial.components.InitialScreenYearlyCountyVignettesCard
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun InitialScreen(
    onSelectCountyVignettes: () -> Unit,
    onConfirmPurchase: () -> Unit
) {
    val viewModel: InitialScreenViewModel = hiltViewModel()
    val state by viewModel.screenState.collectAsState()

    InitialScreenContent(
        screenState = state,
        onCreated = { viewModel.onCreated() },
        onNameVignettePairSelected = { viewModel.onNameVignettePairSelected(it) },
        onConfirmPurchase = {
            viewModel.onConfirmPurchase()
            onConfirmPurchase()
        },
        onSelectCountyVignettes = {
            onSelectCountyVignettes()
            viewModel.clearSelection()
        }
    )
}

@Composable
private fun InitialScreenContent(
    screenState: InitialScreenState,
    onCreated: () -> Unit,
    onNameVignettePairSelected: (Pair<String, Vignette>) -> Unit,
    onConfirmPurchase: () -> Unit,
    onSelectCountyVignettes: () -> Unit
) {
    when (screenState) {
        InitialScreenState.Created -> {
            onCreated()
        }

        InitialScreenState.Error -> {
            InitialScreenErrorContent()
        }

        is InitialScreenState.Loaded -> {
            InitialScreenLoadedContent(
                vehicleInfo = screenState.vehicleInfo,
                nameVignettePairs = screenState.nameVignettePairs,
                selectedNameVignettePair = screenState.selectedNameVignettePair,
                onNameVignettePairSelected = onNameVignettePairSelected,
                onConfirmPurchase = onConfirmPurchase,
                onSelectCountyVignettes = onSelectCountyVignettes
            )
        }

        InitialScreenState.Loading -> {
            InitialScreenLoadingContent()
        }
    }
}

@Composable
private fun InitialScreenLoadingContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(modifier = Modifier.align(Center))
    }
}

@Composable
private fun InitialScreenLoadedContent(
    vehicleInfo: VehicleInfo,
    nameVignettePairs: List<Pair<Int, Vignette>>,
    selectedNameVignettePair: Pair<String, Vignette>?,
    onNameVignettePairSelected: (Pair<String, Vignette>) -> Unit,
    onConfirmPurchase: () -> Unit,
    onSelectCountyVignettes: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.dp_16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_16))
    ) {
        InitialScreenVehicleInfoCard(vehicleInfo)
        InitialScreenCountryVignettesCard(
            nameVignettePairs = nameVignettePairs,
            selectedNameVignettePair = selectedNameVignettePair,
            onNameVignettePairSelected = onNameVignettePairSelected,
            onConfirmPurchase = onConfirmPurchase
        )
        InitialScreenYearlyCountyVignettesCard { onSelectCountyVignettes() }
    }
}

@Composable
private fun InitialScreenErrorContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.error),
            modifier = Modifier.align(Center),
            style = typography.titleSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun InitialScreenContentPreview() {
    MatricAppTheme {
        InitialScreenContent(
            screenState = InitialScreenState.Loaded(
                nameVignettePairs = listOf(
                    Pair(
                        R.string.vignette_type_day,
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.DAY),
                            cost = 4.5,
                            trxFee = 6.7
                        )
                    ),
                    Pair(
                        R.string.vignette_type_day,
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.DAY),
                            cost = 4.5,
                            trxFee = 6.7
                        )
                    ),
                    Pair(
                        R.string.vignette_type_day,
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.DAY),
                            cost = 4.5,
                            trxFee = 6.7
                        )
                    ),
                    Pair(
                        R.string.vignette_type_day,
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.DAY),
                            cost = 4.5,
                            trxFee = 6.7
                        )
                    )
                ),
                vehicleInfo = VehicleInfo(
                    vehicleOwnerName = "Teszt Elek",
                    vehiclePlate = "ABC - 123"
                ),
                selectedNameVignettePair = null
            ),
            onCreated = {},
            onNameVignettePairSelected = {},
            onSelectCountyVignettes = {},
            onConfirmPurchase = {}
        )
    }
}
