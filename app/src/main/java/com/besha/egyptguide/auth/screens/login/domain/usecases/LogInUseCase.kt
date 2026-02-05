package com.besha.egyptguide.auth.screens.login.domain.usecases

import com.besha.egyptguide.auth.screens.login.data.model.LoginRequest
import com.besha.egyptguide.auth.screens.login.domain.repo.LoginRepo

import javax.inject.Inject

class LogInUseCase @Inject constructor(private val loginRepo: LoginRepo) {

    suspend operator fun  invoke(loginRequest: LoginRequest) =  loginRepo.LogIn(loginRequest)

}