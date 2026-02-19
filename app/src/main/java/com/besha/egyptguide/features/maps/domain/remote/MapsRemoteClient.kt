package com.besha.egyptguide.features.maps.domain.remote

import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken

interface MapsRemoteClient {

    suspend fun  onQueryChange(newQuery: String, sessionToken: AutocompleteSessionToken): List<AutocompletePrediction>

}