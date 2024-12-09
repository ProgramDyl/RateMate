package com.example.ratemate.ui.components.styleSheets

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ratemate.data.api.news.NewsItem

@Composable
fun StyledNewsCard(newsItem: NewsItem) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .padding(8.dp)
    ) {
        AsyncImage(
            model = newsItem.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = newsItem.title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = newsItem.description ?: "No description available.",
            fontSize = 14.sp,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .fillMaxWidth(),
            maxLines = 3 // Limiting to 3 lines to avoid run-on sentences
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { /* TODO: Add navigation to full article using newsItem.url */ },
            modifier = Modifier.padding(start = 8.dp, end = 8.dp)
        ) {
            Text("Read More")
        }
    }
}
