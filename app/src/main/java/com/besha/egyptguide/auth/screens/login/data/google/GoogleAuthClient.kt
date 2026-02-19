package com.besha.egyptguide.auth.screens.login.data.google


import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.util.Log
import com.besha.egyptguide.R
import com.besha.egyptguide.auth.screens.login.data.model.GoogleSignInResult
import com.besha.egyptguide.auth.screens.login.data.model.LoginRequest
import com.besha.egyptguide.auth.screens.login.data.model.LoginResponse
import com.besha.egyptguide.auth.screens.signup.data.model.SignUpResponse
import com.besha.egyptguide.auth.screens.login.data.model.UserData
import com.besha.egyptguide.auth.screens.signup.data.model.SignUpRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInRequest.GoogleIdTokenRequestOptions
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException

class GoogleAuthClient(
    private val context: Context,
    private val oneTapClient: SignInClient
) {


    private val auth = Firebase.auth
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun signInRequest(): IntentSender? {

        val result = try {
            oneTapClient.beginSignIn(
                buildSignInRequest()
            ).await()

        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            null
        }
        return result?.pendingIntent?.intentSender
    }


    private fun buildSignInRequest(): BeginSignInRequest {
        return BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId(context.getString(R.string.google_web_client_id))
                    .build()
            ).setAutoSelectEnabled(true)
            .build()
    }

    suspend fun signInResult(intent: Intent): GoogleSignInResult {

        val credential = oneTapClient.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredentials = GoogleAuthProvider.getCredential(googleIdToken, null)
        val email = credential.id // One Tap provides the email here
        Log.d("signInMethods", "email $email")


        return try {
            val signInMethods = auth.fetchSignInMethodsForEmail(email).await().signInMethods ?: emptyList()

            Log.d("signInMethods", signInMethods.toString())



            if (signInMethods.isNotEmpty() && !signInMethods.contains(GoogleAuthProvider.PROVIDER_ID)) {

                Log.d("signInMethods", "email is already registered")

                 return GoogleSignInResult(
                     user = null,
                     errorMessage = "This email is already registered using a password. Please log in with your password."
                 )
            }

            val user = auth.signInWithCredential(googleCredentials).await().user ?: throw IllegalStateException("User creation failed")

            val userDocRef = firestore.collection("users").document(user.uid)
            val snapshot = userDocRef.get().await()

            Log.d("signInMethods", "user $user")

            if (!snapshot.exists()) {
                val userData = hashMapOf(
                    "uid" to user.uid,
                    "email" to user.email,
                    "fullName" to user.displayName,
                    "phoneNumber" to user.phoneNumber,
                    "emailVerified" to user.isEmailVerified,
                    "createdAt" to FieldValue.serverTimestamp(),
                    "provider" to "google"
                )

                userDocRef.set(userData).await()
            }

            GoogleSignInResult(
                user = UserData(
                    userId = user.uid,
                    fullName = user.displayName,
                    email = user.email,
                    phoneNumber = user.phoneNumber,
                    profilePhoto = user.photoUrl?.toString()
                )
                ,
                errorMessage = null
            )


        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            GoogleSignInResult(
                user = null,
                errorMessage = "something went wrong"
            )
        }
    }

    suspend fun signUp(signUpRequest: SignUpRequest): SignUpResponse {
        return try {
            auth.createUserWithEmailAndPassword(signUpRequest.email, signUpRequest.password).await()

            val user = auth.currentUser ?: throw IllegalStateException("User creation failed")
            user.sendEmailVerification().await()



            val userData =
                hashMapOf(
                    "uid" to user.uid,
                    "email" to signUpRequest.email,
                    "fullName" to signUpRequest.fullName,
                    "phoneNumber" to signUpRequest.phoneNumber,
                    "emailVerified" to user.isEmailVerified,
                )


            firestore
                .collection("users")
                .document(user.uid)
                .set(userData)
                .await()



            SignUpResponse(
                isSuccessful = true,
                errorMessage = null
            )
        } catch (e: Exception) {
            SignUpResponse(
                isSuccessful = false,
                errorMessage = e.message
            )
        }
    }



    suspend fun logIn(loginRequest: LoginRequest): LoginResponse {
        return try {
            // 1. Sign in with email & password
            val authResult = auth.signInWithEmailAndPassword(
                loginRequest.email,
                loginRequest.password
            ).await()

            val user = authResult.user ?: throw IllegalStateException("Login failed")


            // 2. Check if email is verified
            if (!user.isEmailVerified) {
                return LoginResponse(
                    isSuccessful = false,
                    errorMessage = "Please verify your email before logging in."
                )
            }

            // 3. Get Firebase ID token (JWT)
            val idTokenResult = user.getIdToken( true).await()
            val idToken = idTokenResult.token
            Log.d("TAG", "Firebase ID Token: $idToken")  // <-- This logs the JWT

            // 4. Return successful login
            LoginResponse(
                isSuccessful = true,
                errorMessage = null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e
            LoginResponse(
                isSuccessful = false,
                errorMessage = e.message ?: "Something went wrong"
            )
        }
    }

    suspend fun signOut(){
        try {
            oneTapClient.signOut().await()
            auth.signOut()
        }catch (e:Exception){
            e.printStackTrace()
            if (e is CancellationException) throw e
        }
    }

    fun getSignedInUser():UserData?= auth.currentUser?.run {
        UserData(
            userId = uid,
            fullName = displayName,
            email = email,
            phoneNumber = phoneNumber,
            profilePhoto = photoUrl?.toString()
        )
    }

}