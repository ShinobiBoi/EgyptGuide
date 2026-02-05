package com.besha.egyptguide.features.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.besha.egyptguide.appcore.navigation.ScreenResources
import com.besha.egyptguide.auth.screens.login.presentation.screen.LogInScreen
import com.besha.egyptguide.auth.screens.signup.presentation.screen.SignUpScreen
import com.besha.egyptguide.features.home.presenation.screen.HomeScreen

@Composable
fun MainScreen(rootController: NavController){

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        val navController = rememberNavController()



        NavHost(navController, startDestination = ScreenResources.HomeRoute) {
            composable<ScreenResources.HomeRoute> {
                HomeScreen(

                )
            }
        }
    }
}