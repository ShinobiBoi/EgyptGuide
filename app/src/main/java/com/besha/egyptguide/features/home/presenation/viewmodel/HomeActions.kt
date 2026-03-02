package com.besha.egyptguide.features.home.presenation.viewmodel

import com.besha.egyptguide.appcore.mvi.Action
import com.google.android.gms.maps.model.LatLng

sealed class HomeActions : Action {

    data class GetHistoricalPlaces(val currentLocation: LatLng) : HomeActions()

    object GetCurrentLocation : HomeActions()


}
