package com.besha.egyptguide.auth.authcore.di

import com.besha.egyptguide.auth.authcore.data.remote.AuthRemoteClientImp
import com.besha.egyptguide.auth.authcore.data.repo.AuthRepoImp
import com.besha.egyptguide.auth.authcore.domain.remote.AuthRemoteClient
import com.besha.egyptguide.auth.authcore.domain.repo.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AuthBindModule {

    @Binds
    abstract fun bindAuthRepo(authRepoImp: AuthRepoImp): AuthRepo

    @Binds
    abstract fun bindAuthRemoteClient(authRemoteClientImp: AuthRemoteClientImp): AuthRemoteClient



}