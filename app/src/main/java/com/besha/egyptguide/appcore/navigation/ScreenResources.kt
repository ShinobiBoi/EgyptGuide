package com.besha.egyptguide.appcore.navigation

import kotlinx.serialization.Serializable

sealed class ScreenResources {

    @Serializable
    object LoginRoute : ScreenResources()

    @Serializable
    object SignUpRoute : ScreenResources()

    @Serializable
    object ForgotPassword : ScreenResources()

    @Serializable
    object SplashRoute : ScreenResources()


    @Serializable
    object AuthRoute : ScreenResources()


    @Serializable
    object MainRoute : ScreenResources()

    @Serializable
    object HomeRoute : ScreenResources()





}