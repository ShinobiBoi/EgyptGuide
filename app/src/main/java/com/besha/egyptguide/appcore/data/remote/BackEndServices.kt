package com.besha.egyptguide.appcore.data.remote

import com.besha.egyptguide.appcore.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface BackEndServices {


    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}