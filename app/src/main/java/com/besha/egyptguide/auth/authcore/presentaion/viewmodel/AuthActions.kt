package com.besha.egyptguide.auth.authcore.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.Action

sealed class AuthActions : Action {
    object IsLoggedIn : AuthActions()
}