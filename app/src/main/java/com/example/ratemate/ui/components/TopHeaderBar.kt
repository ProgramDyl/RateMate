package com.example.ratemate.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeaderBar(navController: NavController) {
    TopAppBar(title = {
        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "RateMate",
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center) // Use BoxScope's align
            )
        }
    }, actions = {
        Icon(imageVector = androidx.compose.material.icons.Icons.Default.Settings,
            contentDescription = "Settings",
            modifier = Modifier
                .clickable {
                    navController.navigate("settings") {
                        popUpTo("bottom_navigation") { inclusive = false }
                        launchSingleTop = true
                    }
                }
                .padding(8.dp),
            tint = Color.White)
    }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Blue)
    )
}

@Preview(showBackground = true)
@Composable
fun TopHeaderBarPreview() {
    val navController = rememberNavController()
    TopHeaderBar(navController = navController)
}