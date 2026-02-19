package com.besha.egyptguide.features.maps.data.remote

import com.besha.egyptguide.features.maps.domain.remote.MapsRemoteClient
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class MapsRemoteClientImp @Inject constructor(private val placesClient: PlacesClient) : MapsRemoteClient {


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

}