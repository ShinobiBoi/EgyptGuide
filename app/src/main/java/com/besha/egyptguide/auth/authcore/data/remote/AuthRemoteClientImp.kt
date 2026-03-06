package com.besha.egyptguide.auth.authcore.data.remote

import com.besha.egyptguide.auth.authcore.domain.remote.AuthRemoteClient
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import javax.inject.Inject

class AuthRemoteClientImp @Inject constructor(): AuthRemoteClient{

    private val auth = Firebase.auth


     override fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }



}