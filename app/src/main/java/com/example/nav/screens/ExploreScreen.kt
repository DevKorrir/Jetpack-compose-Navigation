package com.example.nav.screens

import android.R.attr.onClick
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nav.R

@Composable
fun ExploreScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val allDestinations = listOf(
        Destination("paris", "Paris, France", "Romantic getaway", R.drawable.mhn),
        Destination("tokyo", "Tokyo, Japan", "Urban adventure", R.drawable.mhn),
        Destination("bali", "Bali, Indonesia", "Tropical paradise", R.drawable.mhn),
        Destination("nyc", "New York, USA", "City that never sleeps", R.drawable.mhn),
        Destination("rome", "Rome, Italy", "Historic beauty", R.drawable.mhn),
        Destination("bangkok", "Bangkok, Thailand", "Cultural hub", R.drawable.mhn),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Explore Destinations",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(allDestinations) { destination ->
                DestinationGridCard(
                    destination = destination,
                    onClick = {
                        Toast.makeText(context, "Clicked on ${destination.name}", Toast.LENGTH_LONG).show()
//                        navController.navigate(
//                            Screen.DestinationDetail.route.appendParams(
//                                NavArgs.DESTINATION_ID to destination.id
//                            )
//                        )
                    }
                )
            }
        }
    }
}

@Composable
fun DestinationGridCard(
    destination: Destination,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(150.dp),
        onClick = onClick,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Image placeholder
//            Box(
//                modifier = Modifier
//                    .padding(8.dp)
//                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f))
//            ) {
//                Image(
//                    painter = painterResource(id = destination.imageRes),
//                    contentDescription = null,
//                    modifier = Modifier,
//                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
//                )
//
//            }

            
            Text(
                text = destination.name,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

data class Destination(
    val id: String,
    val name: String,
    val description: String,
    val imageRes: Int
)

data class Trip(
    val id: String,
    val title: String,
    val dates: String,
    val destination: String
)