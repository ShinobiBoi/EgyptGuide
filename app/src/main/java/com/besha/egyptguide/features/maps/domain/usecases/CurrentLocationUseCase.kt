package com.besha.egyptguide.features.maps.domain.usecases

import com.besha.egyptguide.features.maps.domain.repo.MapsRepo
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import javax.inject.Inject

class CurrentLocationUseCase  @Inject constructor(private val mapsRepo: MapsRepo) {
    suspend operator fun invoke() = mapsRepo.getCurrentLocationFlow()

}
