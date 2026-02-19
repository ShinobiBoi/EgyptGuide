package com.besha.egyptguide.features.maps.presentaion.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.besha.egyptguide.features.maps.presentaion.viewmodel.MapsActions
import com.besha.egyptguide.features.maps.presentaion.viewmodel.MapsViewModel
import com.google.android.gms.maps.model.Marker
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(
    viewModel: MapsViewModel = hiltViewModel()
) {
    val state by viewModel.viewStates.collectAsState()
    val cameraPositionState = rememberCameraPositionState()

    Box(modifier = Modifier.fillMaxSize()) {

        // Google Map
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
        ) {
/*            state.selectedPlace.data?.latLng?.let { latLng ->
                Marker(
                    state = MarkerState(position = latLng),
                    title = state.selectedPlace.data?.name ?: "",
                    snippet = state.selectedPlace.data?.address ?: ""
                )
            }*/
        }

        // Search UI
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // TextField
            TextField(
                value = state.query,
                onValueChange = {
                    viewModel.executeAction(
                        MapsActions.OnQueryChange(
                            it,
                            state.sessionToken
                        )
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                placeholder = { Text("Search location") },
                singleLine = true,
                trailingIcon = {
                    if (state.query.isNotEmpty()) {
                        IconButton(onClick = {
                            viewModel.executeAction(
                                MapsActions.OnQueryChange(
                                    "",
                                    state.sessionToken
                                )
                            )
                        }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Clear")
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = Color.Transparent,   // remove underline
                    unfocusedIndicatorColor = Color.Transparent, // remove underline
                    disabledIndicatorColor = Color.Transparent,  // remove underline when disabled
                    focusedContainerColor = MaterialTheme.colorScheme.surface,  // background color
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface  // background color
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Predictions list
            state.predictions.data?.let { predictionsList ->
                if (predictionsList.isNotEmpty()) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = 300.dp),
                        shape = RoundedCornerShape(12.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column() {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        /*                                 viewModel.executeAction(
                                            MapsActions.OnSelectPlace(
                                                prediction.placeId
                                            )
                                        )*/
                                    }
                                    .padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.LocationOn,
                                    contentDescription = "Location",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(24.dp)
                                )

                                Spacer(modifier = Modifier.width(12.dp))

                                Column {
                                    Text(
                                        text = state.query,
                                        style = MaterialTheme.typography.bodyLarge
                                    )
                                    Text(
                                        text = "Search by nearby",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant
                                    )
                                }
                            }

                            LazyColumn {
                                items(predictionsList) { prediction ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                /*                                 viewModel.executeAction(
                                                    MapsActions.OnSelectPlace(
                                                        prediction.placeId
                                                    )
                                                )*/
                                            }
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.LocationOn,
                                            contentDescription = "Location",
                                            tint = MaterialTheme.colorScheme.primary,
                                            modifier = Modifier.size(24.dp)
                                        )

                                        Spacer(modifier = Modifier.width(12.dp))

                                        Column {
                                            Text(
                                                text = prediction.getPrimaryText(null).toString(),
                                                style = MaterialTheme.typography.bodyLarge
                                            )
                                            Text(
                                                text = prediction.getSecondaryText(null).toString(),
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /*    // Animate camera when a place is selected
        LaunchedEffect(state.selectedPlace.data) {
            state.selectedPlace.data?.latLng?.let { latLng ->
                cameraPositionState.animate(
                    update = CameraUpdateFactory.newLatLngZoom(latLng, 16f),
                    durationMs = 800
                )
            }
        }*/
}