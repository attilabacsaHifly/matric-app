package com.appic.matricapp.ui.screens.purchasesuccess

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.appic.matricapp.R
import com.appic.matricapp.ui.theme.MatricAppTheme
import com.appic.matricapp.ui.theme.MatricappLime

@Composable
fun PurchaseSuccessScreen(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MatricappLime)
            .padding(bottom = dimensionResource(R.dimen.dp_24))
    ) {
        Image(
            painter = painterResource(R.drawable.ic_confetti_png),
            contentDescription = "",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop
        )

        Text(
            text = stringResource(R.string.vignettes_purchased_successfully),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.dp_24)),
            style = typography.displayLarge
        )

        Image(
            painter = painterResource(R.drawable.ic_character_png),
            contentDescription = "",
            modifier = Modifier
                .weight(1f)
                .align(End)
                .offset(x = dimensionResource(R.dimen.dp_32))
                .padding(vertical = dimensionResource(R.dimen.dp_16)),
            contentScale = ContentScale.Crop
        )

        Button(
            onClick = onClose,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.dp_24))
        ) {
            Text(
                text = stringResource(R.string.all_right),
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.dp_8)),
                color = Color.White,
                style = typography.titleSmall
            )
        }
    }
}

@Composable
@Preview
private fun PurchaseSuccessScreenPreview() {
    MatricAppTheme {
        PurchaseSuccessScreen {}
    }
}
