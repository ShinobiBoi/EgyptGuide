package com.besha.egyptguide.features.maps.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.Action
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place

sealed class MapsActions() : Action {

    data class OnQueryChange(val newQuery: String, val sessionToken: AutocompleteSessionToken?) : MapsActions()

    data class OnPlaceSelected(val place: Place) : MapsActions()

}