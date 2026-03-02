package com.besha.egyptguide.features.home.di

import com.besha.egyptguide.features.home.data.remote.HomeRemoteClientImp
import com.besha.egyptguide.features.home.data.repo.HomeRepoImp
import com.besha.egyptguide.features.home.domain.remote.HomeRemoteClient
import com.besha.egyptguide.features.home.domain.repo.HomeRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class HomeBind {


    @Binds
    abstract fun bindHomeRepo(homeRepoImp: HomeRepoImp): HomeRepo


    @Binds
    abstract fun bindHomeRemoteClient(homeRemoteClientImp: HomeRemoteClientImp): HomeRemoteClient




}