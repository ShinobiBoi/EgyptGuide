package com.besha.egyptguide.features.profile.domain.usecase

import com.besha.egyptguide.features.profile.domain.repo.ProfileRepo
import javax.inject.Inject

class GetProfileUseCase @Inject constructor(private val profileRepo: ProfileRepo) {
    suspend operator fun invoke() = profileRepo.getProfile()
}