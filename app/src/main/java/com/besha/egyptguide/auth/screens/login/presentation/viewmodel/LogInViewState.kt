package com.besha.egyptguide.auth.screens.login.presentation.viewmodel

import android.content.IntentSender
import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.ViewState

data class LogInViewState (
   val logInState: CommonViewState<Unit> = CommonViewState(),
): ViewState