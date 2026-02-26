package com.besha.egyptguide.features.maps.presentaion.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.besha.egyptguide.R
import com.google.android.libraries.places.api.model.Place


@Composable
fun NearbyPlacesSheet(
    places: List<Place>,
    onPlaceClick: (Place) -> Unit,
    onCloseClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 350.dp)
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

                Icon(
                    modifier = Modifier.clickable(
                        onClick = {
                            onCloseClick()
                        }
                    ),
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close"
                )

            Spacer(modifier = Modifier.width(8.dp))


            Text(
                text = stringResource(R.string.nearby_places),
                style = MaterialTheme.typography.titleLarge
            )

        }



        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(places) { place ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onPlaceClick(place) }
                        .padding(vertical = 12.dp, horizontal = 8.dp)
                ) {
                    Text(
                        text = place.displayName ?: "",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = place.formattedAddress ?: "",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

