package com.besha.egyptguide.auth.authcore.domain.usecase

import com.besha.egyptguide.auth.authcore.domain.repo.AuthRepo
import javax.inject.Inject

class IsLoggedInUseCase @Inject constructor(private val authRepo: AuthRepo) {
    operator fun invoke () = authRepo.isLoggedIn()
}