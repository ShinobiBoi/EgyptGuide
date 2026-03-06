package com.besha.egyptguide.features.profile.data.model

import android.net.Uri

data class UserProfile(
    val uid: String,
    val username: String?,
    val email: String?,
    val phoneNumber: String?,
    val photoUrl: Uri?
)
