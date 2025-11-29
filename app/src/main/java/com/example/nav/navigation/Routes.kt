package com.example.nav.navigation

import android.net.Uri
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
    object Explore : Routes("explore")
    object Profile : Routes("profile")
    object Settings : Routes("settings")
    object About : Routes("about")

    object EditProfile : Routes("edit_profile") {
        fun createRoute(userId: String): String {
            return "edit_profile/${Uri.encode(userId)}"
        }
    }
}

sealed class BottomItemNav(
    val title: String,
    val icon: ImageVector,
    val route: String,
){
    object Home : BottomItemNav("Home", Icons.Default.Home, Routes.Home.route)
    object Explore : BottomItemNav("Explore", Icons.Default.Explore, Routes.Explore.route)
    object Profile : BottomItemNav("Profile", Icons.Default.Person, Routes.Profile.route)
    object Settings : BottomItemNav("Settings", Icons.Default.Settings, Routes.Settings.route)

    companion object {
        val bottomIcon = listOf(
            Home,
            Explore,
            Profile,
            Settings
        )
    }
}