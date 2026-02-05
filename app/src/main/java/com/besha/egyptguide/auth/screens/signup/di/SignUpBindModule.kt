package com.besha.egyptguide.auth.screens.signup.di

import com.besha.egyptguide.auth.screens.signup.data.repo.SignUpRepoImp
import com.besha.egyptguide.auth.screens.signup.domain.repo.SignUpRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class SignUpBindModule {

    @Binds
    abstract fun bindSignUpRepo(signUpRepoImp: SignUpRepoImp): SignUpRepo

}