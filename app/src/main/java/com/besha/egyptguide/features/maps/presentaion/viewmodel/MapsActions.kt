package com.besha.egyptguide.features.maps.presentaion.viewmodel

import android.content.Context
import com.besha.egyptguide.appcore.mvi.Action
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place

sealed class MapsActions() : Action {

    data class OnQueryChange(val newQuery: String, val sessionToken: AutocompleteSessionToken?) : MapsActions()

    data class SelectPlace(val placeId: String, val sessionToken: AutocompleteSessionToken?) : MapsActions()

    data class GetCurrentLocation(val context: Context) : MapsActions()


}