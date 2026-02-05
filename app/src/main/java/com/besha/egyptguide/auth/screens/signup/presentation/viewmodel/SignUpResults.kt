package com.besha.egyptguide.auth.screens.signup.presentation.viewmodel

import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.Result
import com.besha.egyptguide.auth.screens.signup.data.model.SignUpResponse

sealed class SignUpResults : Result<SignUpViewState>{

    data class SignUp(val state: CommonViewState<SignUpResponse>) : SignUpResults(){
        override fun reduce(
            defaultState: SignUpViewState,
            oldState: SignUpViewState
        ): SignUpViewState {
            return oldState.copy(signUpState = state)
        }
    }


}