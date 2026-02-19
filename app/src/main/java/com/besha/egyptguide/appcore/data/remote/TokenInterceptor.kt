package com.besha.egyptguide.appcore.data.remote

import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.FirebaseAuth
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val user = FirebaseAuth.getInstance().currentUser

        // Use Tasks.await to get the token on the background thread
        val token = user?.let {
            try {
                // False means: "Give me the cached token, or refresh if expired"
                Tasks.await(it.getIdToken(false)).token
            } catch (e: Exception) { null }
        }

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $token")
            .build()

        return chain.proceed(request)
    }
}