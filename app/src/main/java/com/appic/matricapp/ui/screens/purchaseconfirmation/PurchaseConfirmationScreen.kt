package com.appic.matricapp.ui.screens.purchaseconfirmation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R

@Composable
fun PurchaseConfirmationScreen() {
    val viewModel: PurchaseConfirmationScreenViewModel = hiltViewModel()

    PurchaseConfirmationScreenContent()
}

@Composable
private fun PurchaseConfirmationScreenContent() {
    Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_20))) {
        Text(
            text = stringResource(R.string.confirm_purchase),
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.dp_6),
                bottom = dimensionResource(R.dimen.dp_8)
            ),
            style = typography.headlineSmall
        )
        HorizontalDivider()
    }
}
