package com.appic.matricapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    background = MatricappGray,
    primary = MatricappNavy,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    surface = MatricappLime,
    surfaceContainerHighest = Color.White
)

@Composable
fun MatricAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
