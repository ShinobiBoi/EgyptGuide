package com.besha.egyptguide.auth.screens.login.domain.usecases

import com.besha.egyptguide.auth.screens.login.domain.repo.LoginRepo
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(private val loginRepo: LoginRepo){
    suspend operator fun  invoke () = loginRepo.signInWithGoogle()

}