package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.appic.matricapp.ui.screens.purchaseconfirmation.components.PurchaseConfirmationScreenFooter
import com.appic.matricapp.ui.screens.purchaseconfirmation.components.PurchaseConfirmationScreenHeader
import com.appic.matricapp.ui.screens.purchaseconfirmation.components.PurchaseConfirmationScreenVignettes

@Composable
fun PurchaseConfirmationScreen(onCancelPurchase: () -> Unit) {
    val viewModel: PurchaseConfirmationScreenViewModel = hiltViewModel()
    val screenState by viewModel.screenState.collectAsState()

    PurchaseConfirmationScreenContent(screenState, {}, onCancelPurchase)
}

@Composable
private fun PurchaseConfirmationScreenContent(
    screenState: PurchaseConfirmationScreenState,
    onConfirmPurchase: () -> Unit,
    onCancelPurchase: () -> Unit
) {
    when (screenState) {
        is PurchaseConfirmationScreenState.Created -> {
            PurchaseConfirmationScreenCreatedContent(
                vehiclePlate = screenState.vehiclePlate,
                nameNameVignettePairs = screenState.nameVignettePairs,
                onConfirmPurchase = onConfirmPurchase,
                onCancelPurchase = onCancelPurchase
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
    nameNameVignettePairs: List<Pair<String, Vignette>>,
    onConfirmPurchase: () -> Unit,
    onCancelPurchase: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.dp_20))
    ) {
        Text(
            text = stringResource(R.string.confirm_purchase),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_6)),
            style = typography.headlineSmall
        )

        PurchaseConfirmationScreenHeader(
            vehiclePlate = vehiclePlate,
            vignetteType = nameNameVignettePairs.first().second.vignetteTypes.first()
        )
        PurchaseConfirmationScreenVignettes(nameNameVignettePairs)
        PurchaseConfirmationScreenFooter(
            amountToPay = nameNameVignettePairs.sumOf { it.second.cost + it.second.trxFee }.toInt(),
            onConfirmPurchase = onConfirmPurchase,
            onCancelPurchase = onCancelPurchase
        )
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
