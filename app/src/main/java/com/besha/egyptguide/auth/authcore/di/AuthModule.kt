package com.besha.egyptguide.auth.authcore.di

import android.content.Context
import com.besha.egyptguide.auth.screens.login.data.google.GoogleAuthClient
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule  {

    @Provides
    @Singleton
    fun provideOneTapClient(
        @ApplicationContext context: Context
    ): SignInClient {
        return Identity.getSignInClient(context)
    }

    @Provides
    @Singleton
    fun provideGoogleAuthClient(
        @ApplicationContext context: Context,
        oneTapClient: SignInClient
    ): GoogleAuthClient {
        return GoogleAuthClient(
            context = context,
            oneTapClient = oneTapClient
        )
    }
}