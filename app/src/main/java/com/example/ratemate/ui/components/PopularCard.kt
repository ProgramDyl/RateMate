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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ratemate.R

@Composable
fun PopularCard(
    flag: Painter, // The image or flag icon
    countryName: String, // Country's name
    exchangeRate: String, // Exchange rate value
    percentageChange: String, // Percentage change
    isPositive: Boolean, // Whether the percentage is positive or negative
    isFavorited: Boolean, // Whether the item is favorited
    onFavoriteClick: () -> Unit // Callback for favorite icon click
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.White, shape = RoundedCornerShape(12.dp))
            .clickable { /* Click action for the card */ }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Country Flag
        Image(
            painter = flag,
            contentDescription = "$countryName flag",
            modifier = Modifier
                .size(40.dp)
                .background(Color.Gray, shape = CircleShape),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Country Name and Exchange Rate
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = countryName,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
            Text(
                text = exchangeRate,
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        // Percentage Change
        Box(
            modifier = Modifier
                .background(
                    if (isPositive) Color(0xFFDFF6DD) else Color(0xFFFFE6E6),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = percentageChange,
                color = if (isPositive) Color(0xFF228B22) else Color(0xFFFF0000),
                fontWeight = FontWeight.Bold,
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        // Favorite Icon
//        Icon(
//            painter = painterResource(
//                id = if (isFavorited) R.drawable.flag_canada else R.drawable.flag_canada
//            ),
//            contentDescription = "Favorite Icon",
//            modifier = Modifier
//                .size(24.dp)
//                .clickable { onFavoriteClick() },
//            tint = if (isFavorited) Color.Red else Color.Gray
//        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PopularCardPreview() {
//    CurrencyCard(
//        flag = painterResource(id = R.drawable.flag_cad),
//        countryName = "Canadian Dollars",
//        exchangeRate = "0.7199",
//        percentageChange = "+3.87%",
//        isPositive = true,
//        isFavorited = false,
//        onFavoriteClick = {}
//    )
//}