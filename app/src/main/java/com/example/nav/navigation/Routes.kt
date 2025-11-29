package com.example.nav.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Routes (
    val route: String,
){
    object Home : Routes("home")
    object  Explore : Routes("explore")
    object Profile : Routes("profile")
    object Settings : Routes("settings")
    object About : Routes("about")

    object EditProfile : Routes("edit_profile/{userId}") {
        fun createRoute(userId: String) = "edit_profile/$userId"
    }




}

sealed class BottomItemNav(
    val title: String,
    val icon: ImageVector,
    val route: String,
){
    object Home : BottomItemNav (
        title = " Home",
        icon = Icons.Default.Home,
        route = Routes.Home.route

    )

    object Explore : BottomItemNav (
        title = "Explore",
        icon = Icons.Default.Explore,
        route = Routes.Explore.route
    )

    object Profile : BottomItemNav (
        title = "Profile",
        icon = Icons.Default.Person,
        route = Routes.Profile.route
    )

    object Settings : BottomItemNav (
        title = "Settings",
        icon = Icons.Default.Settings,
        route = Routes.Settings.route
    )



    companion object {
        val bottomIcons = listOf(
            Home,
            Explore,
            Profile,
            Settings,
        )
    }
}