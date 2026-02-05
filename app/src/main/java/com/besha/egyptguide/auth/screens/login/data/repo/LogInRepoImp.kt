package com.besha.egyptguide.auth.screens.login.data.repo

import android.content.Intent
import android.content.IntentSender
import com.besha.egyptguide.auth.screens.login.data.google.GoogleAuthClient
import com.besha.egyptguide.auth.screens.login.data.model.GoogleSignInResult
import com.besha.egyptguide.auth.screens.login.data.model.LoginRequest
import com.besha.egyptguide.auth.screens.login.data.model.LoginResponse
import com.besha.egyptguide.auth.screens.login.domain.repo.LoginRepo
import javax.inject.Inject


class LogInRepoImp @Inject constructor(private val googleAuthClient: GoogleAuthClient) : LoginRepo {

    override suspend fun LogIn(loginRequest: LoginRequest): LoginResponse {
        return googleAuthClient.logIn(loginRequest)
    }

    override suspend fun signInWithGoogle(): IntentSender? {
        return googleAuthClient.signInRequest()
    }

    override suspend fun signInWithGoogleResult(intent: Intent): GoogleSignInResult {
        return googleAuthClient.signInResult(intent)
    }

}