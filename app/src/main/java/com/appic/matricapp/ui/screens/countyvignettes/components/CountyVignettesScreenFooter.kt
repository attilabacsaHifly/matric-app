package com.appic.matricapp.ui.screens.countyvignettes.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun CountyVignettesScreenFooter(
    amountToPay: Double,
    isConfirmPurchaseEnabled: Boolean,
    onConfirmPurchase: () -> Unit
) {
    Text(text = stringResource(R.string.amount_to_pay), style = typography.bodyLarge)
    Text(
        text = stringResource(R.string.huf, amountToPay.toInt()),
        style = typography.displayLarge
    )

    Button(
        onClick = onConfirmPurchase,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.dp_24)),
        enabled = isConfirmPurchaseEnabled
    ) {
        Text(
            text = stringResource(R.string.next),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8)),
            color = Color.White,
            style = typography.titleSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CountyVignettesScreenFooterPreview() {
    MatricAppTheme {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_20))) {
            CountyVignettesScreenFooter(15650.0, true) {}
        }
    }
}
