package com.example.nav.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomBarNav(
    navController: NavController,
    modifier: Modifier = Modifier// empty
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
    ){

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val currentRoute = currentDestination?.route

        BottomItemNav.bottomIcons.forEach { bottomItemNav ->
            val isSelected = currentDestination?.route == bottomItemNav.route //boolean

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != bottomItemNav.route){
                    navController.navigate(bottomItemNav.route)
                    {
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                    }
                },
                icon = {
                    Icon(
                        imageVector = bottomItemNav.icon,
                        contentDescription = bottomItemNav.title, // accessibility
                        tint = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        }
                    )
                },
                label = {
                    Text(
                        text = bottomItemNav.title,
                        color = if (isSelected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        }
                    )
                }
            )
        }
    }





}