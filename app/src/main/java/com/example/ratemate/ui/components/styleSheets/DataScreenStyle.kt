package com.example.ratemate.ui.components.styleSheets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val cardModifier = Modifier
    .padding(8.dp)
    .fillMaxWidth()

val cardElevation = 4.dp

val titleTextStyle = TextStyle(
    fontSize = 18.sp,
    color = Color.Black,
    fontWeight = FontWeight.Bold
)

val bodyTextStyle = TextStyle(
    fontSize = 16.sp,
    color = Color.Gray
)

val sectionSpacer = Modifier.height(20.dp)
