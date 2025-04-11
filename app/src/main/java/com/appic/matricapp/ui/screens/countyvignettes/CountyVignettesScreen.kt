package com.appic.matricapp.ui.screens.countyvignettes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.countyvignettes.components.CountyVignettesScreenFooter
import com.appic.matricapp.ui.screens.countyvignettes.components.CountyVignettesScreenVignetteList
import com.appic.matricapp.ui.screens.models.Vignette

@Composable
fun CountyVignettesScreen(onConfirmPurchase: () -> Unit) {
    val viewModel: CountyVignettesScreenViewModel = hiltViewModel()

    val countyNameVignettePairs by viewModel.countyNameVignettePairs.collectAsState()
    val amountToPay by viewModel.amountToPay.collectAsState()
    val isContinueEnabled by viewModel.isContinueEnabled.collectAsState()

    CountyVignettesScreenContent(
        countyNameVignettePairs = countyNameVignettePairs,
        amountToPay = amountToPay,
        isContinueEnabled = isContinueEnabled,
        onVignetteSelected = { viewModel.onVignetteSelected(it) },
        onVignetteDeselected = { viewModel.onVignetteDeselected(it) },
        onConfirmPurchase = {
            viewModel.onConfirmPurchase()
            onConfirmPurchase()
        }
    )
}

@Composable
private fun CountyVignettesScreenContent(
    countyNameVignettePairs: List<Pair<String, Vignette>>,
    amountToPay: Double,
    isContinueEnabled: Boolean,
    onVignetteSelected: (Vignette) -> Unit,
    onVignetteDeselected: (Vignette) -> Unit,
    onConfirmPurchase: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.dp_20))
    ) {
        Text(
            text = stringResource(R.string.yearly_county_vignettes),
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.dp_6),
                bottom = dimensionResource(R.dimen.dp_16)
            ),
            style = typography.headlineSmall
        )

        // TODO(Replace placeholder!)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .padding(bottom = dimensionResource(R.dimen.dp_8))
                .background(Color.LightGray)
        ) { }

        CountyVignettesScreenVignetteList(
            countyNameVignettePairs = countyNameVignettePairs,
            onVignetteSelected = onVignetteSelected,
            onVignetteDeselected = onVignetteDeselected
        )
        CountyVignettesScreenFooter(amountToPay, isContinueEnabled, onConfirmPurchase)
    }
}
