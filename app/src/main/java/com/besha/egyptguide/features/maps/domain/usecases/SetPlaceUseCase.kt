package com.besha.egyptguide.features.maps.domain.usecases

import com.besha.egyptguide.features.maps.domain.repo.MapsRepo
import com.google.android.libraries.places.api.model.AutocompleteSessionToken
import com.google.android.libraries.places.api.model.Place
import javax.inject.Inject

class SetPlaceUseCase  @Inject constructor(private val mapsRepo: MapsRepo) {
    suspend operator fun invoke(placeId: String, sessionToken: AutocompleteSessionToken) =
        mapsRepo.selectPlace(placeId, sessionToken)
}
