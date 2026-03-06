package com.besha.egyptguide.features.profile.domain.remote

import com.besha.egyptguide.features.profile.data.model.UserProfile

interface ProfileRemoteClient {

    suspend fun getProfile(): UserProfile

    suspend fun logOut()
}