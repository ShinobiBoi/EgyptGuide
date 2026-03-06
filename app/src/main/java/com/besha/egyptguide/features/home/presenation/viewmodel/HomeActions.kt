package com.besha.egyptguide.features.home.presenation.viewmodel

import com.besha.egyptguide.appcore.mvi.Action
import com.google.android.gms.maps.model.LatLng

sealed class HomeActions : Action {

    data class GetHistoricalPlaces(val currentLocation: LatLng) : HomeActions()
    data class GetHotelPlaces(val currentLocation: LatLng) : HomeActions()
    data class GetRestaurantsPlaces(val currentLocation: LatLng) : HomeActions()
    data class GetAttractionsPlaces(val currentLocation: LatLng) : HomeActions()

    object GetCurrentLocation : HomeActions()


}
