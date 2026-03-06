package com.besha.egyptguide.auth.authcore.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.Result

sealed class AuthResults : Result<AuthViewState> {

    data class LoggedInResult(val isLoggedIn: Boolean) : AuthResults() {
        override fun reduce(
            defaultState: AuthViewState,
            oldState: AuthViewState
        ): AuthViewState {
            return oldState.copy(isLoggedIn = isLoggedIn)
        }
    }
}