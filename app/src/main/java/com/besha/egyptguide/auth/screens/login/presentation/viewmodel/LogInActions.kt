package com.besha.egyptguide.auth.screens.login.presentation.viewmodel

import android.app.Activity
import android.content.Intent
import com.besha.egyptguide.appcore.mvi.Action
import com.besha.egyptguide.auth.screens.login.data.model.LoginRequest

sealed class LogInActions : Action {

    data class LogIn(val loginRequest: LoginRequest) : LogInActions()

    data class GoogleSignIn(val activity: Activity) : LogInActions()

    object ResetState : LogInActions()

}