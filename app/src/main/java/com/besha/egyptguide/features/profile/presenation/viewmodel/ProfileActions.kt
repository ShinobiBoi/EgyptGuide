package com.besha.egyptguide.features.profile.presenation.viewmodel

import com.besha.egyptguide.appcore.mvi.Action

sealed class ProfileActions : Action {
    object GetProfile : ProfileActions()
    object LogOut : ProfileActions()
}