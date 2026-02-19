package com.besha.egyptguide.features.maps.domain.repo

import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken

interface MapsRepo {
    suspend fun  onQueryChange(newQuery: String, sessionToken: AutocompleteSessionToken): List<AutocompletePrediction>

}