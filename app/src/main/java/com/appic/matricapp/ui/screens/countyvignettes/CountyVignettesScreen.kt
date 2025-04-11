package com.appic.matricapp.ui.screens.countyvignettes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R
import com.appic.matricapp.ui.screens.models.Vignette

@Composable
fun CountyVignettesScreen() {
    val viewModel: CountyVignettesScreenViewModel = hiltViewModel()

    val countyNameVignettePairs by viewModel.countyNameVignettePairs.collectAsState()
    val amountToPay by viewModel.amountToPay.collectAsState()
    val isContinueEnabled by viewModel.isContinueEnabled.collectAsState()

    CountyVignettesScreenContent(
        countyNameVignettePairs = countyNameVignettePairs,
        amountToPay = amountToPay,
        isContinueEnabled = isContinueEnabled,
        onVignetteSelected = { viewModel.onVignetteSelected(it) },
        onVignetteDeselected = { viewModel.onVignetteDeselected(it) }
    )
}

@Composable
private fun CountyVignettesScreenContent(
    countyNameVignettePairs: List<Pair<String, Vignette>>,
    amountToPay: Double,
    isContinueEnabled: Boolean,
    onVignetteSelected: (Vignette) -> Unit,
    onVignetteDeselected: (Vignette) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.dp_20))
    ) {
        Text(
            text = stringResource(R.string.yearly_county_vignettes),
            modifier = Modifier.padding(
                top = dimensionResource(R.dimen.dp_6),
                bottom = dimensionResource(R.dimen.dp_16)
            ),
            style = typography.headlineSmall
        )

        // TODO(Replace placeholder!)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .padding(bottom = dimensionResource(R.dimen.dp_8))
                .background(Color.LightGray)
        ) { }

        val checkedStates = remember { countyNameVignettePairs.map { false }.toMutableStateList() }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            itemsIndexed(items = countyNameVignettePairs) { index, item ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = SpaceBetween,
                    verticalAlignment = CenterVertically
                ) {
                    Row(verticalAlignment = CenterVertically) {
                        Checkbox(
                            checked = checkedStates[index],
                            onCheckedChange = { isChecked ->
                                checkedStates[index] = isChecked
                                
                                if (isChecked) {
                                    onVignetteSelected(item.second)
                                } else {
                                    onVignetteDeselected(item.second)
                                }
                            })
                        Text(item.first)
                    }
                    Text(stringResource(R.string.huf, item.second.cost.toInt()))
                }
            }
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_20)))

        Text(text = stringResource(R.string.amount_to_pay), style = typography.bodyLarge)
        Text(
            text = stringResource(R.string.huf, amountToPay.toInt()),
            style = typography.displayLarge
        )

        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = dimensionResource(R.dimen.dp_24)),
            enabled = isContinueEnabled
        ) {
            Text(
                text = stringResource(R.string.next),
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8)),
                color = Color.White,
                style = typography.titleSmall
            )
        }
    }
}
