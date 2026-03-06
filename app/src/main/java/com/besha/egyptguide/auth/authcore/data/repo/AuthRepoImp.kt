package com.besha.egyptguide.auth.authcore.data.repo

import com.besha.egyptguide.auth.authcore.domain.remote.AuthRemoteClient
import com.besha.egyptguide.auth.authcore.domain.repo.AuthRepo
import javax.inject.Inject

class AuthRepoImp @Inject constructor(private val authRemoteClient: AuthRemoteClient) : AuthRepo {
    override fun isLoggedIn(): Boolean {
        return authRemoteClient.isLoggedIn()

    }
}