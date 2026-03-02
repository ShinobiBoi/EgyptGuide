package com.besha.egyptguide.features.home.data.repo

import com.besha.egyptguide.appcore.data.model.MyPlace
import com.besha.egyptguide.features.home.domain.remote.HomeRemoteClient
import com.besha.egyptguide.features.home.domain.repo.HomeRepo
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class HomeRepoImp @Inject constructor(private val homeRemoteClient: HomeRemoteClient): HomeRepo {

    override suspend fun nearBySearch(currentLocation: LatLng, types: List<String>): List<MyPlace> {
        return homeRemoteClient.nearBySearch(currentLocation, types)
    }


}