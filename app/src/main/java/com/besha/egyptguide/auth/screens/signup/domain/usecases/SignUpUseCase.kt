package com.besha.egyptguide.auth.screens.signup.domain.usecases


import com.besha.egyptguide.auth.screens.signup.data.model.SignUpRequest
import com.besha.egyptguide.auth.screens.signup.domain.repo.SignUpRepo
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val signUpRepo: SignUpRepo) {

    suspend operator fun  invoke(signUpRequest: SignUpRequest) =  signUpRepo.signUp(signUpRequest)

}