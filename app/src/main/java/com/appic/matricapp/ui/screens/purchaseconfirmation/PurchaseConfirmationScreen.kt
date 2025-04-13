package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.ui.screens.purchaseconfirmation.components.PurchaseConfirmationScreenHeader
import com.appic.matricapp.ui.screens.purchaseconfirmation.components.PurchaseConfirmationScreenVignettes

@Composable
fun PurchaseConfirmationScreen() {
    val viewModel: PurchaseConfirmationScreenViewModel = hiltViewModel()
    val screenState by viewModel.screenState.collectAsState()

    PurchaseConfirmationScreenContent(screenState)
}

@Composable
private fun PurchaseConfirmationScreenContent(screenState: PurchaseConfirmationScreenState) {
    when (screenState) {
        is PurchaseConfirmationScreenState.Created -> {
            PurchaseConfirmationScreenCreatedContent(
                vehiclePlate = screenState.vehiclePlate,
                displayedNameNameVignettePairs = screenState.displayedNameNameVignettePairs
            )
        }

        PurchaseConfirmationScreenState.Error -> {
            PurchaseConfirmationScreenErrorContent()
        }

        PurchaseConfirmationScreenState.Loading -> {
            PurchaseConfirmationScreenLoadingContent()
        }

        PurchaseConfirmationScreenState.Success -> {
            PurchaseConfirmationScreenSuccessContent()
        }
    }
}

@Composable
private fun PurchaseConfirmationScreenCreatedContent(
    vehiclePlate: String,
    displayedNameNameVignettePairs: List<Pair<String, Vignette>>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.dp_20))
    ) {
        Text(
            text = stringResource(R.string.confirm_purchase),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_6)),
            style = typography.headlineSmall
        )

        PurchaseConfirmationScreenHeader(
            vehiclePlate = vehiclePlate,
            vignetteType = displayedNameNameVignettePairs.first().second.vignetteTypes.first()
        )
        PurchaseConfirmationScreenVignettes(displayedNameNameVignettePairs)
    }
}

@Composable
private fun PurchaseConfirmationScreenErrorContent() {

}

@Composable
private fun PurchaseConfirmationScreenLoadingContent() {

}

@Composable
private fun PurchaseConfirmationScreenSuccessContent() {

}
