package com.besha.egyptguide.features.profile.presenation.viewmodel

import com.besha.egyptguide.appcore.mvi.CommonViewState
import com.besha.egyptguide.appcore.mvi.MVIBaseViewModel
import com.besha.egyptguide.features.profile.domain.usecase.GetProfileUseCase
import com.besha.egyptguide.features.profile.domain.usecase.LogOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logOutUseCase: LogOutUseCase
): MVIBaseViewModel<ProfileActions, ProfileResults, ProfileViewState>() {

    override val defaultViewState: ProfileViewState
        get() = ProfileViewState()

    override fun handleAction(action: ProfileActions): Flow<ProfileResults> = flow {
        when (action) {

            is ProfileActions.GetProfile -> {

                handleGetProfile(this)

            }

            is ProfileActions.LogOut -> {

              logOutUseCase()

            }

        }
    }

    private suspend fun handleGetProfile(collector: FlowCollector<ProfileResults>) {

        val result = getProfileUseCase()

        collector.emit(ProfileResults.ProfileResult(CommonViewState(data = result)))
    }


}
