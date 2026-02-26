package com.besha.egyptguide.features.maps.data.remote

import com.besha.egyptguide.features.maps.domain.remote.MapsRemoteClient
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.CircularBounds
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import com.google.android.libraries.places.api.net.SearchByTextRequest
import com.google.android.libraries.places.api.net.SearchNearbyRequest
import com.google.android.libraries.places.api.net.SearchNearbyResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class MapsRemoteClientImp @Inject constructor(private val placesClient: PlacesClient) :
    MapsRemoteClient {


    override suspend fun onQueryChange(
        newQuery: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction> {

        if (newQuery.isBlank()) {
            return emptyList()
        }


        val request = FindAutocompletePredictionsRequest.builder()
            .setQuery(newQuery)
            .setCountries("EG")
            .setSessionToken(sessionToken)
            .build()

        val result = placesClient.findAutocompletePredictions(request).await()

        return result.autocompletePredictions

    }

    override suspend fun selectPlace(placeId: String, sessionToken: AutocompleteSessionToken): Place {

        val fields = listOf(
            Place.Field.ID,
            Place.Field.DISPLAY_NAME,
            Place.Field.LOCATION,
            Place.Field.FORMATTED_ADDRESS
        )

        val request =
            FetchPlaceRequest.builder(placeId, fields).setSessionToken(sessionToken).build()


        val result = placesClient.fetchPlace(request).await()

        return result.place


    }

    override suspend fun nearBySearch(currentLocation: LatLng,query : String): List<Place>{

        val fields = listOf(
            Place.Field.ID,
            Place.Field.DISPLAY_NAME,
            Place.Field.LOCATION
        )

        val circle = CircularBounds.newInstance(currentLocation, 5000.0)


        val request = SearchByTextRequest.builder(
            query,
            fields
        )
            .setLocationBias(circle)
            .setMaxResultCount(10)
            .build()

        val result = placesClient.searchByText(request).await()




        return result.places

    }





}