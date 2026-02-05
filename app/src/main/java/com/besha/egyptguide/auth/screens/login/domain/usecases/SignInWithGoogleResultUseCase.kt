package com.besha.egyptguide.auth.screens.login.domain.usecases

import android.content.Intent
import com.besha.egyptguide.auth.screens.login.domain.repo.LoginRepo
import javax.inject.Inject

class SignInWithGoogleResultUseCase @Inject constructor(private val loginRepo: LoginRepo){
    suspend operator fun  invoke (intent: Intent) = loginRepo.signInWithGoogleResult(intent)

}