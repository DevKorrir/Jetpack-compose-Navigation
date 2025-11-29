package com.example.nav.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nav.data.UserRepository
import com.example.nav.navigation.Routes

@Composable
fun ProfileScreen(navController: NavController) {
    // Load user data from repository
    // Using a state to trigger recomposition when we return from edit screen
    var refreshKey by remember { mutableStateOf(0) }
    val user = remember(refreshKey) { UserRepository.getUserById("user_123") }

    // Refresh profile data when returning to this screen
    val currentBackStackEntry = navController.currentBackStackEntry
    LaunchedEffect(currentBackStackEntry) {
        // Increment refresh key to reload user data
        refreshKey++
        Log.d("ProfileScreen", "Profile refreshed, user: ${user?.name}")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Profile",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Profile picture
        Box(
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = user?.name?.take(2)?.uppercase() ?: "?",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        // Profile info - now loads from UserRepository
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = user?.name ?: "Unknown User",
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = user?.email ?: "No email",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "User ID: ${user?.id ?: "unknown"}",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        // Simple button to navigate to edit profile WITH argument
        Button(
            onClick = {
                // Navigate to edit profile and pass the user ID using helper
                val route = Routes.EditProfile.createRoute("user_123")
                Log.d("NavDebug", "Navigating to: $route")
                navController.navigate(route)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Edit My Profile")
        }

        // You can also navigate with different user IDs
        Button(
            onClick = {
                val route = Routes.EditProfile.createRoute("user_456")
                Log.d("NavDebug", "Navigating to: $route")
                navController.navigate(route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.secondary
            )
        ) {
            Text("Edit Another Profile")
        }
    }
}