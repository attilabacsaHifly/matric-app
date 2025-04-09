package com.appic.matricapp.ui.screens.initial.components

import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.ui.theme.MatricAppTheme
import com.appic.matricapp.ui.theme.MatricappNavy

@Composable
fun InitialScreenYearlyCountyVignettesCard(onSelectCountyVignettes: () -> Unit) {
    Card(onClick = onSelectCountyVignettes) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.dp_20)),
            horizontalArrangement = SpaceBetween,
            verticalAlignment = CenterVertically
        ) {
            Text(
                text = stringResource(R.string.yearly_county_vignettes),
                style = typography.headlineMedium
            )

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "",
                tint = MatricappNavy
            )
        }
    }
}

@Composable
@Preview
private fun YearlyCountyVignettesCardPreview() {
    MatricAppTheme {
        InitialScreenYearlyCountyVignettesCard {}
    }
}
