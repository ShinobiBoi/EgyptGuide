package com.besha.egyptguide.appcore.data.remote


import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route


// 2. Use an Authenticator for REFRESHING (if the token expires mid-session)
class TokenAuthenticator : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        // If we get a 401, force a fresh token from Firebase
        val user = FirebaseAuth.getInstance().currentUser
        val newTask = user?.getIdToken(true) // True = Force Refresh
        val newToken = Tasks.await(newTask!!).token

        return response.request.newBuilder()
            .header("Authorization", "Bearer $newToken")
            .build()
    }
}