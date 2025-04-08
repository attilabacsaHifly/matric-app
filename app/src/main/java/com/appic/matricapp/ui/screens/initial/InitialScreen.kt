package com.appic.matricapp.ui.screens.initial

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R
import com.appic.matricapp.ui.theme.MatricappNavy

@Composable
fun InitialScreen(onSelectCountyVignettes: () -> Unit, onConfirmPurchase: () -> Unit) {
    val viewModel: InitialScreenViewModel = hiltViewModel()
    val state by viewModel.initialScreenState.collectAsState()

    InitialScreenContent(state, { viewModel.loadScreen() }, onSelectCountyVignettes)
}

@Composable
private fun InitialScreenContent(
    screenState: InitialScreenState,
    loadScreen: () -> Unit,
    onSelectCountyVignettes: () -> Unit
) {
    when (screenState) {
        InitialScreenState.Created -> {
            loadScreen()
        }

        InitialScreenState.Error -> {
            InitialScreenErrorContent()
        }

        is InitialScreenState.Loaded -> {
            InitialScreenLoadedContent(screenState.vehicleInfo, onSelectCountyVignettes)
        }

        InitialScreenState.Loading -> {
            InitialScreenLoadingContent()
        }
    }
}

@Composable
private fun InitialScreenLoadingContent() {
    
}

@Composable
private fun InitialScreenLoadedContent(
    vehicleInfo: VehicleInfo,
    onSelectCountyVignettes: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.dp_16))
    ) {
        Card(onClick = {}) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = dimensionResource(R.dimen.dp_16),
                        start = dimensionResource(R.dimen.dp_32),
                        end = dimensionResource(R.dimen.dp_32)
                    ),
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_16)),
                verticalAlignment = CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_car),
                    contentDescription = ""
                )
                Column {
                    Text(vehicleInfo.vehiclePlate)
                    Text(vehicleInfo.vehicleOwnerName)
                }
            }
        }

        Card(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8))) {
            Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_16))) {
                Text(stringResource(R.string.country_vignettes))
                Button(onClick = {}, modifier = Modifier.fillMaxWidth()) {
                    Text(stringResource(R.string.purchase))
                }
            }
        }

        Card(onClick = onSelectCountyVignettes) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.dp_16)),
                horizontalArrangement = SpaceBetween
            ) {
                Text(stringResource(R.string.yearly_county_vignettes))
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "",
                    tint = MatricappNavy
                )
            }
        }
    }
}

@Composable
private fun InitialScreenErrorContent() {

}
