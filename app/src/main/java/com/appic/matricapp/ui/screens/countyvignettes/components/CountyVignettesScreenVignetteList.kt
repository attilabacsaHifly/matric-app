package com.appic.matricapp.ui.screens.countyvignettes.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.network.models.Category
import com.appic.matricapp.network.models.VignetteCategory
import com.appic.matricapp.network.models.VignetteType
import com.appic.matricapp.ui.screens.models.Vignette
import com.appic.matricapp.ui.theme.MatricAppTheme

@Composable
fun ColumnScope.CountyVignettesScreenVignetteList(
    countyNameVignettePairs: List<Pair<String, Vignette>>,
    onVignetteSelected: (Vignette) -> Unit,
    onVignetteDeselected: (Vignette) -> Unit
) {
    val checkedStates = remember { countyNameVignettePairs.map { false }.toMutableStateList() }
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
    ) {
        itemsIndexed(items = countyNameVignettePairs) { index, item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        checkedStates[index] = !checkedStates[index]

                        if (checkedStates[index]) {
                            onVignetteSelected(item.second)
                        } else {
                            onVignetteDeselected(item.second)
                        }
                    },
                horizontalArrangement = SpaceBetween,
                verticalAlignment = CenterVertically
            ) {
                Row(verticalAlignment = CenterVertically, modifier = Modifier.weight(1f)) {
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
                    Text(
                        text = item.first,
                        style = typography.bodyLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
                Text(
                    text = stringResource(R.string.huf, item.second.cost.toInt()),
                    style = typography.titleSmall
                )
            }
        }
    }

    HorizontalDivider(modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_20)))
}

@Composable
@Preview(showBackground = true)
private fun CountyVignettesScreenVignetteListPreview() {
    MatricAppTheme {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_20))) {
            CountyVignettesScreenVignetteList(
                countyNameVignettePairs = listOf(
                    Pair(
                        "Baranya",
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.YEAR_11),
                            cost = 4574.5,
                            trxFee = 6.7
                        )
                    ),
                    Pair(
                        "Zala",
                        Vignette(
                            category = Category.CAR,
                            vignetteCategory = VignetteCategory.D1,
                            vignetteTypes = listOf(VignetteType.YEAR_29),
                            cost = 6660.5,
                            trxFee = 6.7
                        )
                    )
                ),
                onVignetteSelected = {},
                onVignetteDeselected = {}
            )
        }
    }
}
