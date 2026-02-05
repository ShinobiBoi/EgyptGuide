package com.besha.egyptguide.auth.screens.signup.presentation.viewmodel

import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.ViewState
import com.besha.egyptguide.auth.screens.signup.data.model.SignUpResponse

data class SignUpViewState(
    val signUpState: CommonViewState<SignUpResponse> = CommonViewState()
) : ViewState