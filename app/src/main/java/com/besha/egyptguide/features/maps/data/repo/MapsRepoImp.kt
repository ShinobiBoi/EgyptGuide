package com.besha.egyptguide.features.maps.data.repo

import com.besha.egyptguide.features.maps.domain.remote.MapsRemoteClient
import com.besha.egyptguide.features.maps.domain.repo.MapsRepo
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import javax.inject.Inject

class MapsRepoImp @Inject constructor (private val mapsRemoteClient: MapsRemoteClient): MapsRepo {


    override suspend fun onQueryChange(
        newQuery: String,
        sessionToken: AutocompleteSessionToken
    ): List<AutocompletePrediction> {
        return mapsRemoteClient.onQueryChange(newQuery, sessionToken)
    }


}