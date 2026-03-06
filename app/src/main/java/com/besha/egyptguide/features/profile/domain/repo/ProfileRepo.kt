package com.besha.egyptguide.features.profile.domain.repo

import com.besha.egyptguide.features.profile.data.model.UserProfile

interface ProfileRepo {
    suspend fun getProfile(): UserProfile

    suspend fun logOut()
}