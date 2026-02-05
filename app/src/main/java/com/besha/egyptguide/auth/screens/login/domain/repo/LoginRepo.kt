package com.besha.egyptguide.auth.screens.login.domain.repo

import android.content.Intent
import android.content.IntentSender
import com.besha.egyptguide.auth.screens.login.data.model.GoogleSignInResult
import com.besha.egyptguide.auth.screens.login.data.model.LoginRequest
import com.besha.egyptguide.auth.screens.login.data.model.LoginResponse

interface LoginRepo {

    suspend fun LogIn(loginRequest: LoginRequest): LoginResponse


    suspend fun signInWithGoogle(): IntentSender?

    suspend fun signInWithGoogleResult(intent: Intent): GoogleSignInResult


}