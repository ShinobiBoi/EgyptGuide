package com.besha.egyptguide.features.maps.di

import com.besha.egyptguide.features.maps.data.remote.MapsRemoteClientImp
import com.besha.egyptguide.features.maps.data.repo.MapsRepoImp
import com.besha.egyptguide.features.maps.domain.remote.MapsRemoteClient
import com.besha.egyptguide.features.maps.domain.repo.MapsRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class MapsBindModule {

    @Binds
    abstract fun bindMapsRepo(mapsRepoImp: MapsRepoImp): MapsRepo

    @Binds
    abstract fun bindMapsRemoteClient(mapsRemoteClientImp: MapsRemoteClientImp): MapsRemoteClient



}