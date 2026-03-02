package com.besha.egyptguide.features.home.domain.repo

import com.besha.egyptguide.appcore.data.model.MyPlace
import com.google.android.gms.maps.model.LatLng

interface HomeRepo {

    suspend fun nearBySearch(currentLocation: LatLng, types: List<String>): List<MyPlace>

}