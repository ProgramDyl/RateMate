package com.example.ratemate.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(userName: String, icUserPlaceholder: Int) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Good morning, $userName!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray) // Placeholder background color
        ) {
            // This box simulates the profile picture
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "Historical Data Graph",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Overall Performance Metric",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = { /* Navigate to Home */ }) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.LightGray) // Placeholder color for icons
                )
            }
            IconButton(onClick = { /* Navigate to Currency */ }) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.LightGray) // Placeholder color for icons
                )
            }
            IconButton(onClick = { /* Navigate to Favourites */ }) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.LightGray) // Placeholder color for icons
                )
            }
            IconButton(onClick = { /* Navigate to Convert */ }) {
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.LightGray) // Placeholder color for icons
                )
            }
        }
    }
}
