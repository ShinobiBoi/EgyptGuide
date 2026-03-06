package com.besha.egyptguide.features.home.presenation.viewmodel

import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.MVIBaseViewModel
import com.besha.egyptguide.features.home.domain.usecase.NearBySearchUseCase
import com.besha.egyptguide.features.maps.domain.usecases.CurrentLocationUseCase
import com.besha.egyptguide.features.maps.presentaion.viewmodel.MapsResults
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.PlaceTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val nearBySearchUseCase: NearBySearchUseCase,
    private val getCurrentLocationUseCase: CurrentLocationUseCase,
) : MVIBaseViewModel<HomeActions, HomeResults, HomeViewState>() {


    override val defaultViewState: HomeViewState
        get() = HomeViewState()

    override fun handleAction(action: HomeActions): Flow<HomeResults> = flow {

        when (action) {

            is HomeActions.GetAttractionsPlaces -> {
                handleGetAttractionsPlaces(action.currentLocation, this)
            }

            is HomeActions.GetHistoricalPlaces -> {
                handleGetHistoricalPlaces(action.currentLocation, this)
            }

            is HomeActions.GetHotelPlaces -> {
                handleGetHotelPlaces(action.currentLocation, this)
            }

            is HomeActions.GetRestaurantsPlaces -> {
                handleGetRestaurantsPlaces(action.currentLocation, this)
            }

            is HomeActions.GetCurrentLocation -> {
                try {
                    getCurrentLocationUseCase().collect { latLng ->
                        emit(HomeResults.CurrentLocation(CommonViewState(data = latLng)))

                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


        }

    }


    private suspend fun handleGetAttractionsPlaces(
        currentLocation: LatLng,
        collector: FlowCollector<HomeResults>
    ) {
        collector.emit(HomeResults.AttractionsPlaces(CommonViewState(isLoading = true)))

        val result = nearBySearchUseCase(currentLocation, listOf(PlaceTypes.TOURIST_ATTRACTION))

        collector.emit(HomeResults.AttractionsPlaces(CommonViewState(data = result)))

    }

    private suspend fun handleGetHistoricalPlaces(
        currentLocation: LatLng,
        collector: FlowCollector<HomeResults>
    ) {
        collector.emit(HomeResults.HistoricalPlaces(CommonViewState(isLoading = true)))

        val result = nearBySearchUseCase(
            currentLocation, listOf(
                "museum",
                "monument",
            )
        )

        collector.emit(HomeResults.HistoricalPlaces(CommonViewState(data = result)))
    }


    private suspend fun handleGetHotelPlaces(
        currentLocation: LatLng,
        collector: FlowCollector<HomeResults>
    ) {
        collector.emit(HomeResults.HotelPlaces(CommonViewState(isLoading = true)))

        val result = nearBySearchUseCase(currentLocation, listOf("hotel"))

        collector.emit(HomeResults.HotelPlaces(CommonViewState(data = result)))

    }

    private suspend fun handleGetRestaurantsPlaces(
        currentLocation: LatLng,
        collector: FlowCollector<HomeResults>
    ) {
        collector.emit(HomeResults.RestaurantPLaces(CommonViewState(isLoading = true)))

        val result = nearBySearchUseCase(currentLocation, listOf(PlaceTypes.RESTAURANT))

        collector.emit(HomeResults.RestaurantPLaces(CommonViewState(data = result)))
    }


}
