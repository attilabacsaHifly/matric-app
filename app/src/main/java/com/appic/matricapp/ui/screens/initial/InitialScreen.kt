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
import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun InitialScreen(
    onSelectCountyVignettes: () -> Unit,
    onPurchaseSuccess: () -> Unit
) {
    val viewModel: InitialScreenViewModel = hiltViewModel()
    val state by viewModel.initialScreenState.collectAsState()

    InitialScreenContent(
        screenState = state,
        onCreated = { viewModel.loadScreen() },
        onVignetteSelected = { viewModel.selectedVignette = it },
        onSelectCountyVignettes = onSelectCountyVignettes,
        onPurchaseYearlyVignette = { viewModel.onPurchaseYearlyVignette() }
    )
}

@Composable
private fun InitialScreenContent(
    screenState: InitialScreenState,
    onCreated: () -> Unit,
    onVignetteSelected: (Vignette) -> Unit,
    onSelectCountyVignettes: () -> Unit,
    onPurchaseYearlyVignette: () -> Unit
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
                info = screenState.info,
                vehicleInfo = screenState.vehicleInfo,
                onVignetteSelected = onVignetteSelected,
                onSelectCountyVignettes = onSelectCountyVignettes,
                onPurchaseYearlyVignette = onPurchaseYearlyVignette
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
    info: Info,
    vehicleInfo: VehicleInfo,
    onVignetteSelected: (Vignette) -> Unit,
    onSelectCountyVignettes: () -> Unit,
    onPurchaseYearlyVignette: () -> Unit
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
            vignettes = info.vignettes,
            onVignetteSelected = { onVignetteSelected(it) },
            onConfirmPurchase = onPurchaseYearlyVignette
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
                info = Info(
                    vignettes = listOf(
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.DAY),
                            cost = 4500.5,
                            trxFee = 160.7
                        ),
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.WEEK),
                            cost = 6500.5,
                            trxFee = 160.7
                        ),
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.MONTH),
                            cost = 11500.5,
                            trxFee = 160.7
                        )
                    ),
                    counties = listOf()
                ), vehicleInfo = VehicleInfo(
                    vehicleOwnerName = "Teszt Elek",
                    vehiclePlate = "ABC - 123"
                )
            ),
            onCreated = {},
            onVignetteSelected = {},
            onSelectCountyVignettes = {},
            onPurchaseYearlyVignette = { }
        )
    }
}
