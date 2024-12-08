package com.example.ratemate.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratemate.R
import com.example.ratemate.data.database.currencyToCountryCode
import com.murgupluoglu.flagkit.FlagKit

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
    val flagResource = FlagKit.getResId(context, currencyToCountryCode(currencyCode))
    val resolvedFlagResource = if (flagResource != 0) flagResource else R.drawable.flag_placeholder




    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { onCurrencyClick() } // NAVIGATE TO SPECIFIC CURRENCIES DATA PAGE
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // FLAG
        Image(
            painter = painterResource(id = resolvedFlagResource),
            contentDescription = "$currencyCode flag",
            modifier = Modifier
                .size(40.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))

        // CURRENCY DETAILS
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
            }
        }

        Text(
            text = percentageChange,
            fontSize = 16.sp,
            color = if (isPositive) Color.Green else Color.Red
        )

        // SPACE BETWEEN % AND FAVORITES ICON
        Spacer(modifier = Modifier.width(12.dp))

        // FAVORITES ICON
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
