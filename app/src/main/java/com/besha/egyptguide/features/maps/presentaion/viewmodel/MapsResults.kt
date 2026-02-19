package com.besha.egyptguide.features.maps.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.Result
import com.besha.egyptguide.appcore.mvi.ViewState
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place

sealed class MapsResults (): Result<MapsViewState>{

    data class OnQueryChange(val newQuery: String) : MapsResults() {
        override fun reduce(
            defaultState: MapsViewState,
            oldState: MapsViewState
        ): MapsViewState {
           return oldState.copy(query = newQuery)
        }
    }

    data class Predictions(val predictions: CommonViewState<List<AutocompletePrediction>>):
        MapsResults(){
        override fun reduce(
            defaultState: MapsViewState,
            oldState: MapsViewState
        ): MapsViewState {
            return oldState.copy(predictions = predictions)
        }
    }

    data class refreshToken(val newToken: AutocompleteSessionToken) : MapsResults() {
        override fun reduce(
            defaultState: MapsViewState,
            oldState: MapsViewState
        ): MapsViewState {
            return oldState.copy(sessionToken = newToken)
        }


    }




}


