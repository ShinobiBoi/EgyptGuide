package com.besha.egyptguide.features.maps.domain.repo

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import kotlinx.coroutines.flow.Flow

interface MapsRepo {
    suspend fun  onQueryChange(newQuery: String, sessionToken: AutocompleteSessionToken): List<AutocompletePrediction>

    suspend fun selectPlace(placeId: String, sessionToken: AutocompleteSessionToken): Place


    suspend fun nearBySearch(currentLocation: LatLng,query : String): List<Place>
     fun getCurrentLocationFlow(): Flow<LatLng>


}