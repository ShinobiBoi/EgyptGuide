package com.besha.egyptguide.features.maps.presentaion.viewmodel

import com.besha.egyptguide.appcore.data.model.MyPlace
import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.ViewState
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place

data class MapsViewState (
    val query: String = "",
    val predictions: CommonViewState<List<AutocompletePrediction>> = CommonViewState(data = emptyList()),
    val selectedPlace: CommonViewState<MyPlace> = CommonViewState(),
    val nearByPlaces: CommonViewState<List<MyPlace>> = CommonViewState(),
    val sessionToken : AutocompleteSessionToken?= null,
    val currentLocation : CommonViewState<LatLng> = CommonViewState(),
    val isCurrentlocationLoaded :Boolean = false
) : ViewState