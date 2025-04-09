package com.appic.matricapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    titleSmall = TextStyle(
        color = MatricappNavy,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 24.sp
    ),
    headlineSmall = TextStyle(
        color = MatricappNavy,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 28.sp
    ),
    headlineMedium = TextStyle(
        color = MatricappNavy,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 28.sp
    ),
    bodyMedium = TextStyle(
        color = MatricappNavy,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        lineHeight = 24.sp
    ),
    bodySmall = TextStyle(
        color = MatricappNavy,
        fontSize = 12.sp,
        fontWeight = FontWeight.Light,
        lineHeight = 16.sp
    )
)
