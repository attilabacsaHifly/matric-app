package com.appic.matricapp.ui.screens.purchaseconfirmation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
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
fun PurchaseConfirmationScreenFooter(
    amountToPay: Int,
    onConfirmPurchase: () -> Unit,
    onCancelPurchase: () -> Unit
) {
    Text(text = stringResource(R.string.amount_to_pay), style = typography.bodyLarge)

    Text(
        text = stringResource(id = R.string.huf, amountToPay),
        style = typography.displayLarge
    )

    Button(
        onClick = onConfirmPurchase,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(R.dimen.dp_24),
                bottom = dimensionResource(R.dimen.dp_8)
            )
    ) {
        Text(
            text = stringResource(R.string.purchase),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8)),
            color = Color.White,
            style = typography.titleSmall
        )
    }

    OutlinedButton(
        onClick = onCancelPurchase,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = dimensionResource(R.dimen.dp_8))
    ) {
        Text(
            text = stringResource(R.string.cancel),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8)),
            style = typography.titleSmall
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PurchaseConfirmationScreenFooterPreview() {
    MatricAppTheme {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_20))) {
            PurchaseConfirmationScreenFooter(16550, {}, {})
        }
    }
}
