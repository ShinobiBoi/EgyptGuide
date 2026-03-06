package com.besha.egyptguide.auth.authcore.presentaion.viewmodel

import com.besha.egyptguide.appcore.mvi.MVIBaseViewModel
import com.besha.egyptguide.auth.authcore.domain.usecase.IsLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(private val isLoggedInUseCase: IsLoggedInUseCase) :
    MVIBaseViewModel<AuthActions, AuthResults, AuthViewState>() {

    override val defaultViewState: AuthViewState
        get() = AuthViewState()

    override fun handleAction(action: AuthActions): Flow<AuthResults> = flow {

        when (action){
            is AuthActions.IsLoggedIn ->{
                handleIsLoggedIn(this)

            }

        }

    }

    private suspend fun handleIsLoggedIn(collector: FlowCollector<AuthResults>) {

        val result = isLoggedInUseCase()
        collector.emit(AuthResults.LoggedInResult(result))


    }


}