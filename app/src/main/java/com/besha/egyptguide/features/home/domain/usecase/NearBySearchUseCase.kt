package com.besha.egyptguide.features.home.domain.usecase

import com.besha.egyptguide.features.home.domain.repo.HomeRepo
import com.besha.egyptguide.features.maps.domain.repo.MapsRepo
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class NearBySearchUseCase @Inject constructor(private val homeRepo: HomeRepo){

    suspend operator fun invoke(currentLocation: LatLng, types: List<String>) = homeRepo.nearBySearch(currentLocation, types)

}