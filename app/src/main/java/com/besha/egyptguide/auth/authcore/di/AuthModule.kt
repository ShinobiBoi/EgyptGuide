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

