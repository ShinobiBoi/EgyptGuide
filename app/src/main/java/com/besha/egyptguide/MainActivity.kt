package com.besha.egyptguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.besha.egyptguide.appcore.navigation.ScreenResources
import com.besha.egyptguide.auth.presentaion.screen.AurhScreen
import com.besha.egyptguide.ui.theme.EgyptguideTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EgyptguideTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val navController = rememberNavController()

                    NavHost(
                        navController,
                        startDestination = ScreenResources.AuthRoute,
                        modifier = Modifier.padding(innerPadding)
                    ) {


                        composable<ScreenResources.AuthRoute> {

                            AurhScreen(navController)


                        }


                    }
                }
            }
        }
    }


}