package com.besha.egyptguide.features.home.presenation.viewmodel

import com.besha.egyptguide.appcore.data.model.MyPlace
import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.Result
import com.google.android.gms.maps.model.LatLng

sealed class HomeResults : Result<HomeViewState>{

    data class HistoricalPlaces(val places: CommonViewState<List<MyPlace>>) : HomeResults() {
        override fun reduce(
            defaultState: HomeViewState,
            oldState: HomeViewState
        ): HomeViewState {
            return oldState.copy(historicalPlaces = places)
        }
    }

    data class HotelPlaces(val places: CommonViewState<List<MyPlace>>) : HomeResults() {
        override fun reduce(
            defaultState: HomeViewState,
            oldState: HomeViewState
        ): HomeViewState {
            return oldState.copy(hotelPlaces = places)

        }
    }


    data class RestaurantPLaces(val places: CommonViewState<List<MyPlace>>) : HomeResults() {
        override fun reduce(
            defaultState: HomeViewState,
            oldState: HomeViewState
        ): HomeViewState{
            return oldState.copy(restaurantPlaces = places)
        }
    }

    data class AttractionsPlaces(val places: CommonViewState<List<MyPlace>>) : HomeResults() {
        override fun reduce(
            defaultState: HomeViewState,
            oldState: HomeViewState
        ): HomeViewState {
            return oldState.copy(attractionsPlaces = places)
        }
    }


    data class CurrentLocation(val location: CommonViewState<LatLng>) :HomeResults() {
        override fun reduce(
            defaultState: HomeViewState,
            oldState: HomeViewState
        ): HomeViewState {
            return oldState.copy(location = location)
        }
    }

    data class Loading(val isLoading: Boolean) : HomeResults() {
        override fun reduce(
            defaultState: HomeViewState,
            oldState: HomeViewState
        ): HomeViewState {
            return oldState.copy(isLoading = isLoading)
        }
    }

}
