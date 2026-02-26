package com.besha.egyptguide.auth.screens.login.domain.usecases

import android.app.Activity
import com.besha.egyptguide.auth.screens.login.domain.repo.LoginRepo
import javax.inject.Inject

class GoogleSignInUseCase @Inject constructor(private val loginRepo: LoginRepo){
    suspend operator fun  invoke (activity: Activity) = loginRepo.googleSignIn(activity)
}