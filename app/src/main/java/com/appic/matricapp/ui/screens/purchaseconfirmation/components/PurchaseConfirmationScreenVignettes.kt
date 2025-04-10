package com.appic.matricapp.ui.screens.purchaseconfirmation.components

import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.network.models.Category
import com.appic.matricapp.network.models.VignetteCategory
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun PurchaseConfirmationScreenVignettes(vignettes: List<Vignette>) {
    vignettes.forEach {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.dp_8)),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text("TODO", style = typography.bodyLarge)
            Text(stringResource(R.string.huf, it.cost.toInt()), style = typography.bodyMedium)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(R.dimen.dp_8),
                bottom = dimensionResource(R.dimen.dp_32)
            ),
        horizontalArrangement = SpaceBetween,
        verticalAlignment = CenterVertically
    ) {
        Text(text = stringResource(R.string.trx_fee), style = typography.labelMedium)
        Text(
            text = stringResource(R.string.huf, vignettes.sumOf { it.trxFee }.toInt()),
            style = typography.bodyMedium
        )
    }

    HorizontalDivider()
}

@Composable
@Preview(showBackground = true)
private fun PurchaseConfirmationScreenVignettesPreview(modifier: Modifier = Modifier) {
    MatricAppTheme {
        Column {
            PurchaseConfirmationScreenVignettes(
                listOf(
                    Vignette(
                        category = Category.CAR,
                        vignetteCategory = VignetteCategory.D1,
                        vignetteTypes = listOf(),
                        cost = 4.5,
                        trxFee = 6.7
                    )
                )
            )
        }
    }
}
