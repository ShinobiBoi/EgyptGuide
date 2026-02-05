package com.besha.egyptguide.auth.screens.login.presentation.viewmodel

import android.content.Intent
import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.MVIBaseViewModel
import com.besha.egyptguide.auth.screens.login.data.model.LoginRequest
import com.besha.egyptguide.auth.screens.login.domain.usecases.LogInUseCase
import com.besha.egyptguide.auth.screens.login.domain.usecases.SignInWithGoogleResultUseCase
import com.besha.egyptguide.auth.screens.login.domain.usecases.SignInWithGoogleUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class LogInViewModel @Inject constructor(
    private val logInUseCase: LogInUseCase,
    private val signInWithGoogleUseCase: SignInWithGoogleUseCase,
    private val signInWithGoogleResultUseCase: SignInWithGoogleResultUseCase,
) : MVIBaseViewModel<LogInActions, LogInResults, LogInViewState>() {

    override val defaultViewState: LogInViewState
        get() = LogInViewState()

    override fun handleAction(action: LogInActions): Flow<LogInResults> = flow {

        when (action) {

            is LogInActions.LogIn -> {
                handleLogIn(this, action.loginRequest)
            }

            is LogInActions.GoogleSignIn -> {
                handleGoogleSignIn(this)
            }

            is LogInActions.GoogleSignInResult -> {
                handleGoogleSignInResult(this, action.intent)

            }


            is LogInActions.ResetState -> {

                handleReset(this)

            }

            else -> {

            }
        }

    }

    private suspend fun handleGoogleSignInResult(
        collector: FlowCollector<LogInResults>,
        intent: Intent
    ) {

        val result = signInWithGoogleResultUseCase(intent)

        if (result.user != null) {
            collector.emit(LogInResults.LogIn(CommonViewState(isSuccess = true)))

        } else {
            collector.emit(LogInResults.LogIn(CommonViewState(errorThrowable = Throwable(result.errorMessage ?: "Google sign-in failed"))))
        }

    }

    private suspend fun handleGoogleSignIn(collector: FlowCollector<LogInResults>) {


        collector.emit(LogInResults.LogIn(CommonViewState(isLoading = true)))

        val intentSender = signInWithGoogleUseCase()

        collector.emit(LogInResults.GoogleSignIn(intentSender))

    }

    private suspend fun handleLogIn(
        collector: FlowCollector<LogInResults>,
        loginRequest: LoginRequest
    ) {
        collector.emit(LogInResults.LogIn(CommonViewState(isLoading = true)))

        val result = logInUseCase(loginRequest)

        if (result.isSuccessful) {
            collector.emit(LogInResults.LogIn(CommonViewState(isSuccess = true)))

        } else {
            collector.emit(LogInResults.LogIn(CommonViewState(errorThrowable = Throwable(result.errorMessage))))
        }

    }

    private suspend fun handleReset(
        collector: FlowCollector<LogInResults>,
    ) {
        collector.emit(LogInResults.LogIn(CommonViewState()))
    }


}