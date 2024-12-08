package com.example.ratemate.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratemate.R

@Composable
fun CurrencyCard(
    currencyCode: String,
    exchangeRate: String,
    isPositive: Boolean,
    percentageChange: String,
    isFavorited: Boolean,
    onFavoriteClick: () -> Unit,
    onCurrencyClick: () -> Unit // Added to handle card click
) {
    val context = LocalContext.current
    val flagResource = context.resources.getIdentifier(
        "flag_${currencyCode.lowercase()}",
        "drawable",
        context.packageName
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { onCurrencyClick() } // Navigate to CurrencyDataScreen
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Country flag
        Image(
            painter = painterResource(id = if (flagResource != 0) flagResource else R.drawable.flag_placeholder),
            contentDescription = "$currencyCode flag",
            modifier = Modifier
                .size(40.dp)
//                .background(Color.LightGray, shape = CircleShape) // Placeholder styling
        )
        Spacer(modifier = Modifier.width(12.dp))

        // Currency details
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = currencyCode,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = exchangeRate,
                    fontSize = 14.sp
                )
//                Spacer(modifier = Modifier.width(25.dp))
            }
        }

        Text(
            text = percentageChange,
            fontSize = 16.sp,
            color = if (isPositive) Color.Green else Color.Red
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Favorite icon
        Icon(
            painter = painterResource(
                id = if (isFavorited) R.drawable.favorite_filled else R.drawable.favorite_outline
            ),
            contentDescription = "Favorite Icon",
            modifier = Modifier
                .size(24.dp)
                .clickable { onFavoriteClick() } // Handle favorite toggle
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyCardPreview() {
    CurrencyCard(
        currencyCode = "USD",
        exchangeRate = "1.2345",
        isPositive = true,
        percentageChange = "+0.45%",
        isFavorited = false,
        onFavoriteClick = { /* Toggle favorite */ },
        onCurrencyClick = { /* Navigate to details */ }
    )
}
