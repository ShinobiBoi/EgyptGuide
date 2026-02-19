package com.besha.egyptguide.features.main.presentaion.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.besha.egyptguide.appcore.navigation.ScreenResources
import com.besha.egyptguide.features.home.presenation.screen.HomeScreen
import com.besha.egyptguide.features.main.presentaion.components.CustomBottomNavigationBar
import com.besha.egyptguide.features.main.presentaion.viewmodel.BottomNavViewModel
import com.besha.egyptguide.features.maps.presentaion.screen.MapsScreen

@Composable
fun MainScreen(rootController: NavController) {



    val navController = rememberNavController()
    val bottomNavViewModel = hiltViewModel<BottomNavViewModel>()
    val currentRoute by bottomNavViewModel.currentRoute.collectAsState()

    val lastNavigatedMediaId = rememberSaveable { mutableStateOf(-1) }


    Scaffold(
        bottomBar = {
            CustomBottomNavigationBar(currentRoute) { selectedRoute ->
                if (selectedRoute != currentRoute) {
                    bottomNavViewModel.onRouteSelected(selectedRoute)
                    navController.navigate(selectedRoute) {
                        launchSingleTop = true
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        restoreState = true

                    }
                }
            }
        }

    ) { innerPadding ->
        val x= innerPadding


        NavHost(
            navController,
            startDestination = ScreenResources.HomeRoute,
            modifier = Modifier.padding()
        ) {
            composable<ScreenResources.HomeRoute> {
                HomeScreen(

                )
            }
            composable<ScreenResources.MapsRoute> {

                MapsScreen()

            }
            composable<ScreenResources.ProfileRoute> {

            }
            composable<ScreenResources.ExploreRoute> {

            }
        }
    }
}