package com.besha.egyptguide.auth.authcore.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.ViewState

data class AuthViewState (
    val isLoggedIn: Boolean = false,
): ViewState