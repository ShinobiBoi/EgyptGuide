package com.besha.egyptguide.features.maps.di

import android.content.Context
import com.besha.egyptguide.BuildConfig
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.net.PlacesClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MapsModule {

    @Provides
    @Singleton
    fun providePlacesClient(
        @ApplicationContext context: Context
    ): PlacesClient {

        if (!Places.isInitialized()) {
            Places.initializeWithNewPlacesApiEnabled(context, BuildConfig.MAPS_API_KEY)
        }

        return Places.createClient(context)
    }
}