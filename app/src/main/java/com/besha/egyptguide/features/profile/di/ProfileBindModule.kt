package com.besha.egyptguide.features.profile.di

import com.besha.egyptguide.features.profile.data.remote.ProfileRemoteClientImp
import com.besha.egyptguide.features.profile.data.repo.ProfileRepoImp
import com.besha.egyptguide.features.profile.domain.remote.ProfileRemoteClient
import com.besha.egyptguide.features.profile.domain.repo.ProfileRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ProfileBindModule {

    @Binds
    abstract fun bindProfileRepo(profileRepoImp: ProfileRepoImp): ProfileRepo

    @Binds
    abstract fun bindProfileRemoteClient(profileRemoteClientImp: ProfileRemoteClientImp): ProfileRemoteClient

}