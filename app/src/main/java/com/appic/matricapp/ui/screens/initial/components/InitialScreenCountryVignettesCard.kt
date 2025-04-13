package com.appic.matricapp.ui.screens.initial.components

import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
fun InitialScreenCountryVignettesCard(
    displayedNameVignettePairs: List<Pair<Int, Vignette>>,
    onConfirmPurchase: (Pair<String, Vignette>) -> Unit
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }

    Card {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_16))) {
            Text(
                text = stringResource(R.string.country_vignettes),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.dp_8)),
                style = typography.headlineSmall
            )

            displayedNameVignettePairs.forEachIndexed { index, nameVignettePair ->
                OutlinedCard(
                    onClick = { selectedIndex = index },
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_4)),
                    colors = cardColors(containerColor = Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.dp_16)),
                        horizontalArrangement = SpaceBetween,
                        verticalAlignment = CenterVertically
                    ) {
                        Row(modifier = Modifier.weight(1f), verticalAlignment = CenterVertically) {
                            RadioButton(
                                selected = index == selectedIndex,
                                onClick = { selectedIndex = index }
                            )

                            val displayedName =
                                "${displayedNameVignettePairs[index].second.vignetteCategory.name} - " +
                                        stringResource(displayedNameVignettePairs[index].first)

                            Text(
                                text = displayedName,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = typography.bodyLarge
                            )
                        }

                        Text(
                            text = stringResource(
                                R.string.huf,
                                nameVignettePair.second.cost.toInt()
                            ),
                            style = typography.titleSmall
                        )
                    }
                }
            }

            val displayedName = selectedIndex?.let {
                "${displayedNameVignettePairs[it].second.vignetteCategory.name} - " +
                        stringResource(displayedNameVignettePairs[it].first)
            } ?: ""
            Button(
                onClick = {
                    selectedIndex?.let {
                        onConfirmPurchase(
                            Pair(displayedName, displayedNameVignettePairs[it].second)
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.dp_8)),
                enabled = selectedIndex != null
            ) {
                Text(
                    text = stringResource(R.string.purchase),
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8)),
                    color = Color.White,
                    style = typography.titleSmall
                )
            }
        }
    }
}

@Composable
@Preview
private fun InitialScreenCountryVignettesCardPreview() {
    MatricAppTheme {
        InitialScreenCountryVignettesCard(
            displayedNameVignettePairs = listOf(
                Pair(
                    R.string.vignette_type_day,
                    Vignette(
                        category = Category.CAR,
                        vignetteCategory = VignetteCategory.D1,
                        vignetteTypes = listOf(VignetteType.DAY),
                        cost = 5600.0,
                        trxFee = 110.0
                    )
                ),
                Pair(
                    R.string.vignette_type_week,
                    Vignette(
                        category = Category.CAR,
                        vignetteCategory = VignetteCategory.D1,
                        vignetteTypes = listOf(VignetteType.DAY),
                        cost = 5600.0,
                        trxFee = 110.0
                    )
                ),
                Pair(
                    R.string.vignette_type_month,
                    Vignette(
                        category = Category.CAR,
                        vignetteCategory = VignetteCategory.D1,
                        vignetteTypes = listOf(VignetteType.DAY),
                        cost = 5600.0,
                        trxFee = 110.0
                    )
                ),
                Pair(
                    R.string.vignette_type_month,
                    Vignette(
                        category = Category.CAR,
                        vignetteCategory = VignetteCategory.D1,
                        vignetteTypes = listOf(VignetteType.DAY),
                        cost = 5600.0,
                        trxFee = 110.0
                    )
                )
            ),
            onConfirmPurchase = {}
        )
    }
}
