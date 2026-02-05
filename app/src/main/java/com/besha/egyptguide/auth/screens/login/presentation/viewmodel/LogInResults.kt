package com.besha.egyptguide.auth.screens.login.presentation.viewmodel

import android.content.IntentSender
import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.Result

sealed class LogInResults : Result<LogInViewState> {

    data class LogIn(val state: CommonViewState<Unit>) : LogInResults() {
        override fun reduce(
            defaultState: LogInViewState,
            oldState: LogInViewState
        ): LogInViewState {
            return oldState.copy(
                logInState = state
            )
        }
    }

    data class GoogleSignIn(val intentSender: IntentSender?) : LogInResults(){
        override fun reduce(
            defaultState: LogInViewState,
            oldState: LogInViewState
        ): LogInViewState {
            return oldState.copy(
                intentSender = intentSender
            )
        }
    }

}