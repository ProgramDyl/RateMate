package com.example.ratemate.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    fontSize: TextUnit = 16.sp,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = fontSize,
        modifier = modifier
    )
}
