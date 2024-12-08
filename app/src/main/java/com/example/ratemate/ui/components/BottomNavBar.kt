package com.example.ratemate.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.ratemate.ui.navigation.BottomNavItem

@Composable
fun BottomNavBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit
) {
    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(30.dp) // ICON SIZES - SHOULD SCALE BAR WITH IT
                    )
                },
                label = { Text(item.label) },
                selected = navController.currentDestination?.route == item.route,
                onClick = { onItemClick(item) }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun BottomNavBarPreview() {
    val navController = rememberNavController()

    BottomNavBar(
        items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Currency,
            BottomNavItem.Favorites,
            BottomNavItem.Convert
        ),
        navController = navController,
        onItemClick = {}
    )
}