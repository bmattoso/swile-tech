package com.br.swile.tech.core.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.br.swile.tech.R

val UbuntuFont = FontFamily(
    Font(R.font.ubuntu_bold),
    Font(R.font.ubuntu_bold_italic),
    Font(R.font.ubuntu_light),
    Font(R.font.ubuntu_light_italic),
    Font(R.font.ubuntu_italic),
    Font(R.font.ubuntu_medium),
    Font(R.font.ubuntu_medium_italic),
    Font(R.font.ubuntu_regular),
)

val Typography = Typography(
    bodyLarge = TextStyle(fontFamily = UbuntuFont),
    titleLarge = TextStyle(
        fontFamily = UbuntuFont,
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,
        lineHeight = 41.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = UbuntuFont,
        fontWeight = FontWeight.W500,
        fontSize = 15.sp,
        lineHeight = 20.sp,
        fontStyle = FontStyle.Normal
    ),
    labelSmall = TextStyle(
        fontFamily = UbuntuFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontStyle = FontStyle.Normal
    ),
    labelMedium = TextStyle(
        fontFamily = UbuntuFont,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        fontStyle = FontStyle.Normal
    )
)