package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

    PurchaseConfirmationScreenContent("ABC- 123", listOf()) // TODO
}

@Composable
private fun PurchaseConfirmationScreenContent(vehiclePlate: String, vignettes: List<Vignette>) {
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

        PurchaseConfirmationScreenHeader(vehiclePlate, vignettes.first().vignetteTypes.first())
        PurchaseConfirmationScreenVignettes(vignettes)
    }
}
