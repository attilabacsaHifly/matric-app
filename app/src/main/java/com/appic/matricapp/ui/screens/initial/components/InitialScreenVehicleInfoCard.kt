package com.appic.matricapp.ui.screens.initial.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.models.VehicleInfo
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun InitialScreenVehicleInfoCard(vehicleInfo: VehicleInfo) {
    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.dp_16),
                    start = dimensionResource(R.dimen.dp_32),
                    end = dimensionResource(R.dimen.dp_32),
                    bottom = dimensionResource(R.dimen.dp_4)
                ),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_16)),
            verticalAlignment = CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_car),
                contentDescription = ""
            )

            Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dp_4))) {
                Text(text = vehicleInfo.vehiclePlate, style = typography.bodyLarge)
                Text(text = vehicleInfo.vehicleOwnerName, style = typography.bodySmall)
            }
        }
    }
}

@Composable
@Preview
private fun VehicleInfoCardPreview(modifier: Modifier = Modifier) {
    MatricAppTheme {
        InitialScreenVehicleInfoCard(VehicleInfo("Teszt Elek", "ABC - 123"))
    }
}
