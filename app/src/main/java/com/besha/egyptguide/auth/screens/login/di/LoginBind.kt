package com.besha.egyptguide.auth.screens.login.di


import com.besha.egyptguide.auth.screens.login.data.repo.LogInRepoImp
import com.besha.egyptguide.auth.screens.login.domain.repo.LoginRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class LoginBind {


    @Binds
    abstract fun bindLoginRepo(logInRepoImp: LogInRepoImp): LoginRepo


}