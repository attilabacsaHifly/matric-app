package com.appic.matricapp.ui.screens.countyvignettes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.appic.matricapp.R

@Composable
fun CountyVignettesScreen() {
    val viewModel: CountyVignettesScreenViewModel = hiltViewModel()

    CountyVignettesScreenContent()
}

@Composable
private fun CountyVignettesScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.dp_20))
    ) {
        Text(
            text = stringResource(R.string.yearly_county_vignettes),
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_6)),
            style = typography.headlineSmall
        )
    }
}
