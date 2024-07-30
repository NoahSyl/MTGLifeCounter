package com.example.mtglifecounter.ui.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.mtglifecounter.R

/*FUENTES*/

val CustomFontFamily = FontFamily(
    Font(R.font.planewalkerbold)
)

val customTextStyle = TextStyle(
    fontFamily = CustomFontFamily,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp

)