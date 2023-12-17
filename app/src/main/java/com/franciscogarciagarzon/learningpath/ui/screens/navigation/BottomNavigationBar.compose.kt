package com.franciscogarciagarzon.learningpath.ui.screens.navigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults.colors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    homeNavigation: () -> Unit = {},
    favNavigation: () -> Unit = {},
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        NavigationBarItem(
            selected = true,
            colors = colors(
                selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                indicatorColor = MaterialTheme.colorScheme.inversePrimary,
                unselectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                unselectedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
            onClick = {
                Log.d("NavigationBar", "Home clicked")
                homeNavigation()
            },
            label = { Text("Home") },
            icon = { Icon(Icons.Default.Home, "Home", tint = MaterialTheme.colorScheme.surfaceTint) }
        )
        NavigationBarItem(
            selected = false,
            onClick = {
                Log.d("NavigationBar", "FAV clicked")
                favNavigation()
            },
            label = { Text("Fav") },
            icon = { Icon(Icons.Default.Favorite, "Fav", tint = MaterialTheme.colorScheme.surfaceTint) }
        )
    }

}

