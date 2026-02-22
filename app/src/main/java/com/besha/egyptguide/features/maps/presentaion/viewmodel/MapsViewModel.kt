package com.besha.egyptguide.features.maps.presentaion.viewmodel

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.MVIBaseViewModel
import com.besha.egyptguide.features.maps.domain.usecases.CurrentLocationUseCase
import com.besha.egyptguide.features.maps.domain.usecases.QueryChangeUseCase
import com.besha.egyptguide.features.maps.domain.usecases.SetPlaceUseCase
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(
    private val queryChangeUseCase: QueryChangeUseCase,
    private val setPlaceUseCase: SetPlaceUseCase,
    private val getCurrentLocationUseCase: CurrentLocationUseCase
) : MVIBaseViewModel<MapsActions, MapsResults, MapsViewState>() {


    override val defaultViewState: MapsViewState
        get() = MapsViewState()

    override fun handleAction(action: MapsActions): Flow<MapsResults> = flow {

        when (action) {

            is MapsActions.OnQueryChange -> {
                handleQueryChange(action.newQuery, action.sessionToken, this)

            }

            is MapsActions.SelectPlace -> {

                handleSelectPlace(action.placeId, action.sessionToken, this)

            }
                is MapsActions.GetCurrentLocation -> {
                    try {
                       getCurrentLocationUseCase().collect { latLng ->
                            emit(MapsResults.CurrentLocation(CommonViewState(data = latLng)))
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            else -> {}

        }

    }


    private suspend fun handleSelectPlace(
        placeId: String,
        sessionToken: AutocompleteSessionToken?,
        collector: FlowCollector<MapsResults>
    ) {

        if (sessionToken == null) {

            val newToken = AutocompleteSessionToken.newInstance()
            collector.emit(MapsResults.refreshToken(newToken))

            val place = setPlaceUseCase(placeId, newToken)

            collector.emit(MapsResults.SelectedPlace(CommonViewState(data = place)))
            collector.emit(MapsResults.refreshToken(null))

        } else {
            val place = setPlaceUseCase(placeId, sessionToken)
            collector.emit(MapsResults.SelectedPlace(CommonViewState(data = place)))
            collector.emit(MapsResults.refreshToken(null))

        }


    }

    private suspend fun handleQueryChange(
        newQuery: String,
        sessionToken: AutocompleteSessionToken?,
        collector: FlowCollector<MapsResults>
    ) {
        collector.emit(MapsResults.OnQueryChange(newQuery))

        if (sessionToken == null) {
            val newToken = AutocompleteSessionToken.newInstance()

            collector.emit(MapsResults.refreshToken(newToken))


            val predictions = queryChangeUseCase(newQuery, newToken)

            if (!predictions.isEmpty()) {
                collector.emit(MapsResults.Predictions(CommonViewState(data = predictions)))
            } else {
                collector.emit(MapsResults.Predictions(CommonViewState(data = emptyList())))
            }

        } else {
            val predictions = queryChangeUseCase(newQuery, sessionToken)

            if (!predictions.isEmpty()) {
                collector.emit(MapsResults.Predictions(CommonViewState(data = predictions)))

            } else {
                collector.emit(MapsResults.Predictions(CommonViewState(data = emptyList())))
            }
        }
    }


}