package com.besha.egyptguide.features.profile.data.remote

import android.provider.ContactsContract
import com.besha.egyptguide.features.profile.data.model.UserProfile
import com.besha.egyptguide.features.profile.domain.remote.ProfileRemoteClient
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import javax.inject.Inject

class ProfileRemoteClientImp @Inject constructor() : ProfileRemoteClient {

    private val auth = Firebase.auth



    override suspend fun getProfile(): UserProfile {
        val user = auth.currentUser ?: throw IllegalStateException("User not logged in")

        return UserProfile(
            user.uid,
            user.displayName,
            user.email,
            user.phoneNumber,
            user.photoUrl
        )


    }

    override suspend fun logOut() {
        auth.signOut()
    }


}