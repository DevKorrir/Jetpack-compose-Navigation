package com.example.nav.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.nav.screens.AboutScreen
import com.example.nav.screens.EditProfileScreen
import com.example.nav.screens.ExploreScreen
import com.example.nav.screens.HomeScreen
import com.example.nav.screens.ProfileScreen
import com.example.nav.screens.SettingsScreen


@Composable
fun Navgraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    val bottomRoutes = BottomItemNav.bottomIcons.map { it.route }
    val shouldShowBottomBar = currentRoute in bottomRoutes



    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (shouldShowBottomBar) {
                BottomBarNav(navController)
            }
        }

    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = Routes.Home.route,
            modifier = modifier.padding(innerPadding)
        ) {

            composable(Routes.Home.route) {
                HomeScreen(
                    navController = navController
                )
            }

            composable(Routes.Explore.route) {
                ExploreScreen(
                    navController = navController
                )
            }

            composable(Routes.Profile.route) {
                ProfileScreen(
                    navController = navController
                )
            }

            composable(Routes.Settings.route) {
                SettingsScreen(
                    navController = navController
                )
            }

            composable(Routes.About.route) {
                AboutScreen(navController = navController)
            }

            composable(
                route = "edit_profile/{userId}",
                arguments = listOf(navArgument("userId") { type = NavType.StringType })
            ) { backStackEntry ->
                val userId = backStackEntry.arguments?.getString("userId") ?: "unknown"
//                android.util.Log.d("NavDebug", "EditProfile screen - Received userId: $userId")
//                android.util.Log.d("NavDebug", "EditProfile screen - All arguments: ${backStackEntry.arguments}")
                EditProfileScreen(
                    userId = userId,
                    onNavigateBack = {
                        navController.navigateUp()
                        //navController.popBackStack()
                    }
                )
            }
        }
    }


}