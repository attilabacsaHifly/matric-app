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
    nameVignettePairs: List<Pair<Int, Vignette>>,
    selectedNameVignettePair: Pair<String, Vignette>?,
    onNameVignettePairSelected: (Pair<String, Vignette>) -> Unit,
    onConfirmPurchase: () -> Unit
) {
    Card {
        Column(modifier = Modifier.padding(dimensionResource(R.dimen.dp_16))) {
            Text(
                text = stringResource(R.string.country_vignettes),
                modifier = Modifier.padding(bottom = dimensionResource(R.dimen.dp_8)),
                style = typography.headlineSmall
            )

            val displayedNameVignettePairs = nameVignettePairs.map {
                Pair("${it.second.vignetteCategory.name} - ${stringResource(it.first)}", it.second)
            }
            displayedNameVignettePairs.forEachIndexed { index, displayedNameVignettePair ->
                OutlinedCard(
                    onClick = { onNameVignettePairSelected(displayedNameVignettePair) },
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
                                selected = index == displayedNameVignettePairs.indexOf(
                                    selectedNameVignettePair
                                ),
                                onClick = { onNameVignettePairSelected(displayedNameVignettePair) }
                            )
                            Text(
                                text = displayedNameVignettePair.first,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 1,
                                style = typography.bodyLarge
                            )
                        }

                        Text(
                            text = stringResource(
                                R.string.huf,
                                displayedNameVignettePair.second.cost.toInt()
                            ),
                            style = typography.titleSmall
                        )
                    }
                }
            }

            Button(
                onClick = onConfirmPurchase,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = dimensionResource(R.dimen.dp_8)),
                enabled = selectedNameVignettePair != null
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
            nameVignettePairs = listOf(
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
            selectedNameVignettePair = null,
            onNameVignettePairSelected = {},
            onConfirmPurchase = {}
        )
    }
}
