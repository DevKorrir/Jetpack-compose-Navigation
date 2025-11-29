package com.example.nav.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nav.navigation.Routes


@Composable
fun HomeScreen(
    navController: NavController
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Welcome to Wanderlust!",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        
        Text(
            text = "Where will your next adventure take you?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(bottom = 24.dp)
        )

//        FeaturedDestinationsSection(
//            destinations = featuredDestinations,
//            onDestinationClick = { destinationId ->
//                navController.navigate(
//                    Screen.DestinationDetail.route.appendParams(
//                        NavArgs.DESTINATION_ID to destinationId
//                    )
//                )
//            }
//        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate(Routes.Explore.route) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Explore All Destinations")
        }
    }
}

//@Composable
//fun FeaturedDestinationsSection(
//    destinations: List<Destination>,
//    onDestinationClick: (String) -> Unit
//) {
//    Column {
//        Text(
//            text = "Featured Destinations",
//            style = MaterialTheme.typography.headlineSmall,
//            modifier = Modifier.padding(bottom = 16.dp)
//        )
//
//        LazyRow(
//            horizontalArrangement = Arrangement.spacedBy(16.dp)
//        ) {
//            items(destinations) { destination ->
//                FeaturedDestinationCard(
//                    destination = destination,
//                    onClick = { onDestinationClick(destination.id) }
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun FeaturedDestinationCard(destination: Destination, onClick: () -> Unit) {
//    Card(
//        modifier = Modifier
//            .width(280.dp)
//            .height(200.dp),
//        onClick = onClick,
//        elevation = CardDefaults.cardElevation(4.dp)
//    ) {
//        Box(modifier = Modifier.fillMaxSize()) {
//            // Image would go here - using placeholder for demo
//            Box(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
//            )
//
//            Column(
//                modifier = Modifier
//                    .align(Alignment.BottomStart)
//                    .padding(16.dp)
//            ) {
//                Text(
//                    text = destination.name,
//                    style = MaterialTheme.typography.titleMedium,
//                    color = MaterialTheme.colorScheme.onSurface
//                )
//                Text(
//                    text = destination.description,
//                    style = MaterialTheme.typography.bodySmall,
//                    color = MaterialTheme.colorScheme.onSurfaceVariant
//                )
//            }
//        }
//    }
//}