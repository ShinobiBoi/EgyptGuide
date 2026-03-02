package com.besha.egyptguide.features.home.domain.remote

import com.besha.egyptguide.appcore.data.model.MyPlace
import com.google.android.gms.maps.model.LatLng

interface HomeRemoteClient {

    suspend fun nearBySearch(currentLocation: LatLng, types: List<String>): List<MyPlace>



}