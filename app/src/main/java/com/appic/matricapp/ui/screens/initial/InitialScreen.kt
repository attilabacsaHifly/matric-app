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
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.initial.components.InitialScreenCountryVignettesCard
import com.appic.matricapp.ui.screens.initial.components.InitialScreenVehicleInfoCard
import com.appic.matricapp.ui.screens.initial.components.InitialScreenYearlyCountyVignettesCard
import com.appic.matricapp.ui.screens.models.Info
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.screens.models.Vignette

@Composable
fun InitialScreen(onSelectCountyVignettes: () -> Unit, onConfirmPurchase: () -> Unit) {
    val viewModel: InitialScreenViewModel = hiltViewModel()
    val state by viewModel.initialScreenState.collectAsState()

    InitialScreenContent(
        screenState = state,
        loadScreen = { viewModel.loadScreen() },
        { viewModel.onVignetteSelected(it) },
        onSelectCountyVignettes = onSelectCountyVignettes,
        onConfirmPurchase = onConfirmPurchase
    )
}

@Composable
private fun InitialScreenContent(
    screenState: InitialScreenState,
    loadScreen: () -> Unit,
    onVignetteSelected: (Vignette) -> Unit,
    onSelectCountyVignettes: () -> Unit,
    onConfirmPurchase: () -> Unit
) {
    when (screenState) {
        InitialScreenState.Created -> {
            loadScreen()
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
                onConfirmPurchase = onConfirmPurchase
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
    onConfirmPurchase: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.dp_16)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_16))
    ) {
        InitialScreenVehicleInfoCard(vehicleInfo)
        InitialScreenCountryVignettesCard(info, { onVignetteSelected(it) }, onConfirmPurchase)
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
