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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun PurchaseConfirmationScreenHeader(vehiclePlate: String, vignetteType: VignetteType) {
    HorizontalDivider()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(R.dimen.dp_16),
                bottom = dimensionResource(R.dimen.dp_8)
            ),
        horizontalArrangement = SpaceBetween
    ) {
        Text(text = stringResource(R.string.vehicle_plate), style = typography.bodyMedium)
        Text(text = vehiclePlate, style = typography.bodyMedium)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(R.dimen.dp_8),
                bottom = dimensionResource(R.dimen.dp_16)
            ),
        horizontalArrangement = SpaceBetween
    ) {
        Text(text = stringResource(R.string.vignette_type), style = typography.bodyMedium)
        Text(text = stringResource(vignetteType.toStringResource()), style = typography.bodyMedium)
    }

    HorizontalDivider(modifier = Modifier.padding(bottom = dimensionResource(R.dimen.dp_24)))
}

@Composable
@Preview(showBackground = true)
private fun PurchaseConfirmationScreenHeaderPreview() {
    MatricAppTheme {
        Column {
            PurchaseConfirmationScreenHeader("ABC - 123", VignetteType.YEAR)
        }
    }
}

private fun VignetteType.toStringResource(): Int {
    return when (this) {
        VignetteType.DAY -> R.string.vignette_type_day
        VignetteType.WEEK -> R.string.vignette_type_week
        VignetteType.MONTH -> R.string.vignette_type_month
        VignetteType.YEAR -> R.string.vignette_type_year
        else -> R.string.vignette_type_year_county
    }
}
