package com.besha.egyptguide.features.maps.presentaion.viewmodel

import android.content.Context
import com.besha.egyptguide.appcore.mvi.Action
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place

sealed class MapsActions() : Action {

    data class OnQueryChange(val newQuery: String, val sessionToken: AutocompleteSessionToken?) : MapsActions()

    data class SelectPlace(val placeId: String, val sessionToken: AutocompleteSessionToken?) : MapsActions()


    object EmptySelectedPlace : MapsActions()

    data class NearBySearch(val currentLocation: LatLng, val query : String) : MapsActions()

    object EmptyNearBySearch : MapsActions()

    data class ResetState(val sessionToken: AutocompleteSessionToken?,val currentLocation: LatLng,val isLocationLoaded : Boolean) : MapsActions()

    object GetCurrentLocation : MapsActions()
    object CurrentLocationLoaded : MapsActions()


}