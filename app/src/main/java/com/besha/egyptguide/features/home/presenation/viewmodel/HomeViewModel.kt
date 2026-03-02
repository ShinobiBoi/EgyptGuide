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
        get()= HomeViewState()

    override fun handleAction(action: HomeActions): Flow<HomeResults> = flow {

        when (action){
            is HomeActions.GetHistoricalPlaces ->{
                handleGetHistoricalPlaces(action.currentLocation,this)


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

    private suspend fun handleGetHistoricalPlaces(
        currentLocation: LatLng,
        collector: FlowCollector<HomeResults>
    ) {
        val result = nearBySearchUseCase(currentLocation, listOf(PlaceTypes.TOURIST_ATTRACTION))

        collector.emit(HomeResults.GetHistoricalPlaces(CommonViewState(data = result)))
    }


}
