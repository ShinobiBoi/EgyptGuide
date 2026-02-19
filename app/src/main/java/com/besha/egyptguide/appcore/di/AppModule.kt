package com.besha.egyptguide.appcore.di

import com.besha.egyptguide.appcore.data.model.User
import com.besha.egyptguide.appcore.data.remote.BackEndServices
import com.besha.egyptguide.appcore.data.remote.TokenAuthenticator
import com.besha.egyptguide.appcore.data.remote.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = "http://10.0.2.2:8000/"



    @Provides
    @Singleton
    fun providesOkHttpClient( ): OkHttpClient =
        OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor())
        .authenticator(TokenAuthenticator())
        .build()



    @Provides
    @Singleton
    fun provideRetrofitInstance(baseUrl: String,client: OkHttpClient): Retrofit {


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApiServices(retrofit: Retrofit): BackEndServices =
        retrofit.create(BackEndServices::class.java)


/*
    @Provides
    @Singleton
    fun provideSessionManager(@ApplicationContext context: Context): SessionManager {
        return SessionManager(context)
    }*/



}