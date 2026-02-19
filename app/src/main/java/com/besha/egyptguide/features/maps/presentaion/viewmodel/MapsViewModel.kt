package com.besha.egyptguide.features.maps.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.MVIBaseViewModel
import com.besha.egyptguide.features.maps.domain.usecases.QueryChangeUseCase
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class MapsViewModel @Inject constructor(
    private val queryChangeUseCase: QueryChangeUseCase
) : MVIBaseViewModel<MapsActions, MapsResults, MapsViewState>() {


    override val defaultViewState: MapsViewState
        get() = MapsViewState()

    override fun handleAction(action: MapsActions): Flow<MapsResults> = flow {

        when (action) {

            is MapsActions.OnQueryChange -> {
                handleQueryChange(action.newQuery, action.sessionToken, this)

            }

            else -> {}

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